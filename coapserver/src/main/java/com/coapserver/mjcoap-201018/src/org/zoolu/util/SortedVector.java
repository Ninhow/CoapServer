/*
 * Copyright (c) 2018 Luca Veltri, University of Parma
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package org.zoolu.util;


import java.util.*;


/** A vector that further guarantees that its elements are added and maintained in ascending order,
  * sorted according to the natural ordering of the elements (see Comparable),
  * or by a Comparator provided at sorted vector creation time.
  * <p>
  * All elements inserted into an sorted vector must implement the Comparable interface
  * (or be accepted by the specified Comparator).
  * Furthermore, all such elements must be mutually comparable: e1.compareTo(e2) (or comparator.compare(e1, e2))
  * must not throw a ClassCastException for any elements e1 and e2 in the sorted set.
  */
public class SortedVector {
	
	/** The comparator associated with this sorted vector */
	Comparator comparator=null;

	/** The actual vector */
	Vector v=null;



	/** Creates a new SortedVector. */
	public SortedVector() {
		v=new Vector();
	}


	/** Creates a new SortedVector.
	  * @param elements elements to be inserted into this vector */
	public SortedVector(Collection elements) {
		v=new Vector(elements);
		sort(v);
	}


	/** Creates a new SortedVector.
	  * @param comparator the comparator that will be used to sort this set. A null value indicates that the elements' natural ordering should be used */
	public SortedVector(Comparator comparator) {
		this.comparator=comparator;
		v=new Vector();
	}


	/** Gets the vector size.
	  * @return vector size */
	public int size() {
		return v.size();
	}


	/** Removes all of the elements from this vector. */
	public void clear() {
		v.clear();
	}


	/** Adds all of the elements in the specified collection to this vector.
	  * @param elements elements to be inserted into this vector */
	public synchronized void addElements(Collection elements) {
		//v.addAll(elements);
		//sort(v);
		for (Iterator i=elements.iterator(); i.hasNext(); ) {
			v.addElement(i.next());
		}
	}


	/** Adds all of the elements in the specified collection to this vector.
	  * @param elements elements to be inserted into this vector */
	public synchronized void addElements(Object[] elements) {
		for (int i=0; i<elements.length; i++) {
			v.addElement(elements[i]);
		}
	}


	/** Adds the specified element to this vector maintaining the ascending order. The vector size is incremented by one.
	  * If one ore more elements with the same order are already present, the new element is added after them.
	  * @param obj the element to be added */
	public synchronized void addElement(Object obj) {
		int i=v.size();
		if (comparator!=null) {
			while (i>0 && comparator.compare(obj,v.elementAt(i-1))<0) i--;
		}
		else {
			while (i>0 && ((Comparable)obj).compareTo((Comparable)v.elementAt(i-1))<0) i--;
		}
		v.insertElementAt(obj,i);
	}


	/** Gets the element at the specified index.
	  * @param i an index into this vector
	  * @return the element at the specified index */
	public Object elementAt(int i) {
		return v.elementAt(i);
	}


	/** Removes the element at the specified index.
	  * @param i an index into this vector */
	public synchronized void removeElementAt(int i) {
		v.removeElementAt(i);
	}


	/** Gets an enumeration of the components of this vector. The returned Enumeration object will generate all items in this vector.
	  * The first item generated is the item at index 0, then the item at index 1, and so on.
	  * @return an enumeration of the components of this vector */
	public Enumeration elements() {
		return v.elements();
	}


	/** Gets an iterator over the elements in this list in proper sequence.
	  * @return an iterator over the elements in this list in proper sequence. */
	public Iterator iterator() {
		return v.iterator();
	}


	/** Gets a new vector containing all of the elements in this vector in the correct order.
	  * @return a new vector containing of the elements in this list in proper sequence */
	public Vector toVector() {
		return new Vector(v);
	}


	/** Gets an array containing all of the elements in this vector in the correct order.
	  * @return an array containing all of the elements in this list in proper sequence */
	public Object[] toArray() {
		return v.toArray();
	}


	/** Gets an array containing all of the elements in this vector in the correct order.
	  * The runtime type of the returned array is that of the specified array.
	  * If the vector fits in the specified array, it is returned therein.
	  * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this vector.
	  * @param a the specified array
	  * @return an array containing all of the elements in this list in proper sequence */
	public Object[] toArray(Object[] a) {
		return v.toArray(a);
	}


	/** Sorts the elements of a vector. */
	/*private static void sort(Vector v) {
		int n=v.size()-1;
		while (n>0) {
			int i_max=0;
			for (int i=0; i<n; i++) {
				Comparable obj_i=(Comparable)v.elementAt(i);
				Comparable obj_ip1=(Comparable)v.elementAt(i+1);
				if (obj_i.compareTo(obj_ip1)>0) {
					v.setElementAt(obj_ip1,i);
					v.setElementAt(obj_i,i+1);
					i_max=i;
				}
			}
			n=i_max;
		}
	}*/


	/** Sorts the elements of a vector. */
	/*private static void sort(Vector v) {
		Object[] array=v.toArray();
		int n=v.size()-1;
		while (n>0) {
			int i_max=0;
			for (int i=0; i<n; i++) {
				int ip1=i+1;
				Comparable obj_i=(Comparable)array[i];
				Comparable obj_ip1=(Comparable)array[ip1];
				if (obj_i.compareTo(obj_ip1)>0) {
					array[i]=obj_ip1;
					array[ip1]=obj_i;
					i_max=i;
				}
			}
			n=i_max;
		}
		for (int i=0; i<array.length; i++) v.set(i,array[i]);
	}*/


	/** Sorts the elements of a vector. */
	private static void sort(Vector v) {
		java.util.Collections.sort(v);
	}

}
