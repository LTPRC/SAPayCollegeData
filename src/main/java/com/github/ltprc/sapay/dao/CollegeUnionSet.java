package com.github.ltprc.sapay.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import com.github.ltprc.sapay.bean.College;
import com.github.ltprc.sapay.bean.UnionSetNode;

public enum CollegeUnionSet {

    INSTANCE;

    /**
     * College -> Node
     * Will not be changed any more.
     */
    private static Map<College, UnionSetNode<College>> nodes = new ConcurrentHashMap<>();

    /**
     * Node -> Parent node
     */
    private static Map<UnionSetNode<College>, UnionSetNode<College>> parents = new ConcurrentHashMap<>();

    /**
     * Ancestor node -> Size
     */
    private static Map<UnionSetNode<College>, Integer> sizes = new ConcurrentHashMap<>();

    public static Map<College, UnionSetNode<College>> getNodes() {
        return nodes;
    }

    public static Map<UnionSetNode<College>, UnionSetNode<College>> getParents() {
        return parents;
    }

    public static Map<UnionSetNode<College>, Integer> getSizes() {
        return sizes;
    }

    public static void forcedAddNode(College college, College relatedCollege) {
        UnionSetNode<College> node = new UnionSetNode<>(college);
        if (nodes.containsKey(college)) {
            node = nodes.get(college);
        }
        UnionSetNode<College> parent = new UnionSetNode<>(relatedCollege);
        if (nodes.containsKey(relatedCollege)) {
            parent = nodes.get(relatedCollege);
        }
        parents.put(node, parent);
        int size = college.getSize();
        if (size > 0) {
            sizes.put(node, size);
        }
    }

    public static int addNode(List<College> colleges) {
        int result = 0;
        for (College college : colleges) {
            result += addNode(college);
        }
        return result;
    }

    public static int addNode(College college) {
        UnionSetNode<College> node = new UnionSetNode<>(college);
        nodes.put(college, node);
        parents.put(node, node);
        sizes.put(node, 1);
        return 1;
    }

    /**
     * Find the ancestor of the college record through the linking relations.
     * @param College
     * @return
     */
    public static UnionSetNode<College> findAncestor(UnionSetNode<College> cur) {
        Stack<UnionSetNode<College>> path = new Stack<>();
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        while (!path.isEmpty()) {
            parents.put(path.pop(), cur);
        }
        return cur;
    }

    public static boolean isSameSet(College a, College b) {
        if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
            return false;
        }
        return findAncestor(nodes.get(a)) == findAncestor(nodes.get(b));
    }

    public static void union(College a, College b) {
        if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
            return;
        }
        UnionSetNode<College> aAncestor = findAncestor(nodes.get(a));
        UnionSetNode<College> bAncestor = findAncestor(nodes.get(b));
        if (aAncestor != bAncestor) {
            int aSize = sizes.get(aAncestor);
            int bSize = sizes.get(bAncestor);
            parents.put(aAncestor, bAncestor);
            sizes.put(bAncestor, aSize + bSize);
            sizes.remove(aAncestor);
        }
    }

    public static int removeNode(List<College> colleges) {
        int result = 0;
        for (College college : colleges) {
            result += removeNode(college);
        }
        return result;
    }

    public static int removeNode(College college) {
        UnionSetNode<College> node = nodes.get(college);
        if (null == node) {
            return 0;
        }

        UnionSetNode<College> parent = parents.get(node);
        if (parent == node) {
            parent = null;
        }
        for (Entry<UnionSetNode<College>, UnionSetNode<College>> entry : parents.entrySet()) {
            if (node == entry.getValue() && null != entry.getKey()) {
                if (null == parent) {
                    parent = entry.getKey();
                }
                parents.put(entry.getKey(), parent);
            }
        }

        UnionSetNode<College> ancestor = findAncestor(parent);
        Integer size = sizes.get(ancestor);
        if (null != size) {
            sizes.put(ancestor, sizes.get(ancestor) - 1);
        }

        nodes.remove(college);
        parents.remove(node);
        sizes.remove(node);
        return 1;
    }

    public static List<College> getCollegeList() {
        List<College> result = new ArrayList<>();
        for (Entry<College, UnionSetNode<College>> entry : nodes.entrySet()) {
            College college = entry.getKey();
            UnionSetNode<College> node = entry.getValue();
            college.setRelatedCollegeId(parents.get(node).getInstance().getCollegeId());
            college.setSize(sizes.get(node));
        }
        return result;
    }
}
