package com.github.ltprc.sapay.bean;

/**
 * Node for the union set.
 * @author tuoli
 *
 */
public class UnionSetNode<T> {

    private T instance;

    /**
     * Pointer is not necessary in this union set data structure.
     */

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public UnionSetNode(T instance) {
        this.instance = instance;
    }
}
