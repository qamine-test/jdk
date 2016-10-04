/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.nbming.directory;

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.NoSuchElementException;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.NbmingEnumerbtion;
import jbvbx.nbming.OperbtionNotSupportedException;

/**
  * This interfbce represents bn bttribute bssocibted with b nbmed object.
  *<p>
  * In b directory, nbmed objects cbn hbve bssocibted with them
  * bttributes.  The <tt>Attribute</tt> interfbce represents bn bttribute bssocibted
  * with b nbmed object.  An bttribute contbins 0 or more, possibly null, vblues.
  * The bttribute vblues cbn be ordered or unordered (see <tt>isOrdered()</tt>).
  * If the vblues bre unordered, no duplicbtes bre bllowed.
  * If the vblues bre ordered, duplicbtes bre bllowed.
  *<p>
  * The content bnd representbtion of bn bttribute bnd its vblues is defined by
  * the bttribute's <em>schemb</em>. The schemb contbins informbtion
  * bbout the bttribute's syntbx bnd other properties bbout the bttribute.
  * See <tt>getAttributeDefinition()</tt> bnd
  * <tt>getAttributeSyntbxDefinition()</tt>
  * for detbils regbrding how to get schemb informbtion bbout bn bttribute
  * if the underlying directory service supports schembs.
  *<p>
  * Equblity of two bttributes is determined by the implementbtion clbss.
  * A simple implementbtion cbn use <tt>Object.equbls()</tt> to determine equblity
  * of bttribute vblues, while b more sophisticbted implementbtion might
  * mbke use of schemb informbtion to determine equblity.
  * Similbrly, one implementbtion might provide b stbtic storbge
  * structure which simply returns the vblues pbssed to its
  * constructor, while bnother implementbtion might define <tt>get()</tt> bnd
  * <tt>getAll()</tt>.
  * to get the vblues dynbmicblly from the directory.
  *<p>
  * Note thbt updbtes to <tt>Attribute</tt> (such bs bdding or removing b
  * vblue) do not bffect the corresponding representbtion of the bttribute
  * in the directory.  Updbtes to the directory cbn only be effected
  * using operbtions in the <tt>DirContext</tt> interfbce.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see BbsicAttribute
  * @since 1.3
  */
public interfbce Attribute extends Clonebble, jbvb.io.Seriblizbble {
    /**
      * Retrieves bn enumerbtion of the bttribute's vblues.
      * The behbviour of this enumerbtion is unspecified
      * if the bttribute's vblues bre bdded, chbnged,
      * or removed while the enumerbtion is in progress.
      * If the bttribute vblues bre ordered, the enumerbtion's items
      * will be ordered.
      *
      * @return A non-null enumerbtion of the bttribute's vblues.
      * Ebch element of the enumerbtion is b possibly null Object. The object's
      * clbss is the clbss of the bttribute vblue. The element is null
      * if the bttribute's vblue is null.
      * If the bttribute hbs zero vblues, bn empty enumerbtion
      * is returned.
      * @exception NbmingException
      *         If b nbming exception wbs encountered while retrieving
      *         the vblues.
      * @see #isOrdered
      */
    NbmingEnumerbtion<?> getAll() throws NbmingException;

    /**
      * Retrieves one of this bttribute's vblues.
      * If the bttribute hbs more thbn one vblue bnd is unordered, bny one of
      * the vblues is returned.
      * If the bttribute hbs more thbn one vblue bnd is ordered, the
      * first vblue is returned.
      *
      * @return A possibly null object representing one of
      *        the bttribute's vblue. It is null if the bttribute's vblue
      *        is null.
      * @exception NbmingException
      *         If b nbming exception wbs encountered while retrieving
      *         the vblue.
      * @exception jbvb.util.NoSuchElementException
      *         If this bttribute hbs no vblues.
      */
    Object get() throws NbmingException;

    /**
      * Retrieves the number of vblues in this bttribute.
      *
      * @return The nonnegbtive number of vblues in this bttribute.
      */
    int size();

    /**
      * Retrieves the id of this bttribute.
      *
      * @return The id of this bttribute. It cbnnot be null.
      */
    String getID();

    /**
      * Determines whether b vblue is in the bttribute.
      * Equblity is determined by the implementbtion, which mby use
      * <tt>Object.equbls()</tt> or schemb informbtion to determine equblity.
      *
      * @pbrbm bttrVbl The possibly null vblue to check. If null, check
      *  whether the bttribute hbs bn bttribute vblue whose vblue is null.
      * @return true if bttrVbl is one of this bttribute's vblues; fblse otherwise.
      * @see jbvb.lbng.Object#equbls
      * @see BbsicAttribute#equbls
      */
    boolebn contbins(Object bttrVbl);
    /**
      * Adds b new vblue to the bttribute.
      * If the bttribute vblues bre unordered bnd
      * <tt>bttrVbl</tt> is blrebdy in the bttribute, this method does nothing.
      * If the bttribute vblues bre ordered, <tt>bttrVbl</tt> is bdded to the end of
      * the list of bttribute vblues.
      *<p>
      * Equblity is determined by the implementbtion, which mby use
      * <tt>Object.equbls()</tt> or schemb informbtion to determine equblity.
      *
      * @pbrbm bttrVbl The new possibly null vblue to bdd. If null, null
      *  is bdded bs bn bttribute vblue.
      * @return true if b vblue wbs bdded; fblse otherwise.
      */
    boolebn bdd(Object bttrVbl);

    /**
      * Removes b specified vblue from the bttribute.
      * If <tt>bttrvbl</tt> is not in the bttribute, this method does nothing.
      * If the bttribute vblues bre ordered, the first occurrence of
      * <tt>bttrVbl</tt> is removed bnd bttribute vblues bt indices grebter
      * thbn the removed
      * vblue bre shifted up towbrds the hebd of the list (bnd their indices
      * decremented by one).
      *<p>
      * Equblity is determined by the implementbtion, which mby use
      * <tt>Object.equbls()</tt> or schemb informbtion to determine equblity.
      *
      * @pbrbm bttrvbl The possibly null vblue to remove from this bttribute.
      * If null, remove the bttribute vblue thbt is null.
      * @return true if the vblue wbs removed; fblse otherwise.
      */
    boolebn remove(Object bttrvbl);

    /**
      * Removes bll vblues from this bttribute.
      */
    void clebr();

    /**
      * Retrieves the syntbx definition bssocibted with the bttribute.
      * An bttribute's syntbx definition specifies the formbt
      * of the bttribute's vblue(s). Note thbt this is different from
      * the bttribute vblue's representbtion bs b Jbvb object. Syntbx
      * definition refers to the directory's notion of <em>syntbx</em>.
      *<p>
      * For exbmple, even though b vblue might be
      * b Jbvb String object, its directory syntbx might be "Printbble String"
      * or "Telephone Number". Or b vblue might be b byte brrby, bnd its
      * directory syntbx is "JPEG" or "Certificbte".
      * For exbmple, if this bttribute's syntbx is "JPEG",
      * this method would return the syntbx definition for "JPEG".
      * <p>
      * The informbtion thbt you cbn retrieve from b syntbx definition
      * is directory-dependent.
      *<p>
      * If bn implementbtion does not support schembs, it should throw
      * OperbtionNotSupportedException. If bn implementbtion does support
      * schembs, it should define this method to return the bppropribte
      * informbtion.
      * @return The bttribute's syntbx definition. Null if the implementbtion
      *    supports schembs but this pbrticulbr bttribute does not hbve
      *    bny schemb informbtion.
      * @exception OperbtionNotSupportedException If getting the schemb
      *         is not supported.
      * @exception NbmingException If b nbming exception occurs while getting
      *         the schemb.
      */

    DirContext getAttributeSyntbxDefinition() throws NbmingException;

    /**
      * Retrieves the bttribute's schemb definition.
      * An bttribute's schemb definition contbins informbtion
      * such bs whether the bttribute is multivblued or single-vblued,
      * the mbtching rules to use when compbring the bttribute's vblues.
      *
      * The informbtion thbt you cbn retrieve from bn bttribute definition
      * is directory-dependent.
      *
      *<p>
      * If bn implementbtion does not support schembs, it should throw
      * OperbtionNotSupportedException. If bn implementbtion does support
      * schembs, it should define this method to return the bppropribte
      * informbtion.
      * @return This bttribute's schemb definition. Null if the implementbtion
      *     supports schembs but this pbrticulbr bttribute does not hbve
      *     bny schemb informbtion.
      * @exception OperbtionNotSupportedException If getting the schemb
      *         is not supported.
      * @exception NbmingException If b nbming exception occurs while getting
      *         the schemb.
      */
    DirContext getAttributeDefinition() throws NbmingException;

    /**
      * Mbkes b copy of the bttribute.
      * The copy contbins the sbme bttribute vblues bs the originbl bttribute:
      * the bttribute vblues bre not themselves cloned.
      * Chbnges to the copy will not bffect the originbl bnd vice versb.
      *
      * @return A non-null copy of the bttribute.
      */
    Object clone();

    //----------- Methods to support ordered multivblued bttributes

    /**
      * Determines whether this bttribute's vblues bre ordered.
      * If bn bttribute's vblues bre ordered, duplicbte vblues bre bllowed.
      * If bn bttribute's vblues bre unordered, they bre presented
      * in bny order bnd there bre no duplicbte vblues.
      * @return true if this bttribute's vblues bre ordered; fblse otherwise.
      * @see #get(int)
      * @see #remove(int)
      * @see #bdd(int, jbvb.lbng.Object)
      * @see #set(int, jbvb.lbng.Object)
      */
    boolebn isOrdered();

    /**
     * Retrieves the bttribute vblue from the ordered list of bttribute vblues.
     * This method returns the vblue bt the <tt>ix</tt> index of the list of
     * bttribute vblues.
     * If the bttribute vblues bre unordered,
     * this method returns the vblue thbt hbppens to be bt thbt index.
     * @pbrbm ix The index of the vblue in the ordered list of bttribute vblues.
     * {@code 0 <= ix < size()}.
     * @return The possibly null bttribute vblue bt index <tt>ix</tt>;
     *   null if the bttribute vblue is null.
     * @exception NbmingException If b nbming exception wbs encountered while
     * retrieving the vblue.
     * @exception IndexOutOfBoundsException If <tt>ix</tt> is outside the specified rbnge.
     */
    Object get(int ix) throws NbmingException;

    /**
     * Removes bn bttribute vblue from the ordered list of bttribute vblues.
     * This method removes the vblue bt the <tt>ix</tt> index of the list of
     * bttribute vblues.
     * If the bttribute vblues bre unordered,
     * this method removes the vblue thbt hbppens to be bt thbt index.
     * Vblues locbted bt indices grebter thbn <tt>ix</tt> bre shifted up towbrds
     * the front of the list (bnd their indices decremented by one).
     *
     * @pbrbm ix The index of the vblue to remove.
     * {@code 0 <= ix < size()}.
     * @return The possibly null bttribute vblue bt index <tt>ix</tt> thbt wbs removed;
     *   null if the bttribute vblue is null.
     * @exception IndexOutOfBoundsException If <tt>ix</tt> is outside the specified rbnge.
     */
    Object remove(int ix);

    /**
     * Adds bn bttribute vblue to the ordered list of bttribute vblues.
     * This method bdds <tt>bttrVbl</tt> to the list of bttribute vblues bt
     * index <tt>ix</tt>.
     * Vblues locbted bt indices bt or grebter thbn <tt>ix</tt> bre
     * shifted down towbrds the end of the list (bnd their indices incremented
     * by one).
     * If the bttribute vblues bre unordered bnd blrebdy hbve <tt>bttrVbl</tt>,
     * <tt>IllegblStbteException</tt> is thrown.
     *
     * @pbrbm ix The index in the ordered list of bttribute vblues to bdd the new vblue.
     * {@code 0 <= ix <= size()}.
     * @pbrbm bttrVbl The possibly null bttribute vblue to bdd; if null, null is
     * the vblue bdded.
     * @exception IndexOutOfBoundsException If <tt>ix</tt> is outside the specified rbnge.
     * @exception IllegblStbteException If the bttribute vblues bre unordered bnd
     * <tt>bttrVbl</tt> is one of those vblues.
     */
    void bdd(int ix, Object bttrVbl);


    /**
     * Sets bn bttribute vblue in the ordered list of bttribute vblues.
     * This method sets the vblue bt the <tt>ix</tt> index of the list of
     * bttribute vblues to be <tt>bttrVbl</tt>. The old vblue is removed.
     * If the bttribute vblues bre unordered,
     * this method sets the vblue thbt hbppens to be bt thbt index
     * to <tt>bttrVbl</tt>, unless <tt>bttrVbl</tt> is blrebdy one of the vblues.
     * In thbt cbse, <tt>IllegblStbteException</tt> is thrown.
     *
     * @pbrbm ix The index of the vblue in the ordered list of bttribute vblues.
     * {@code 0 <= ix < size()}.
     * @pbrbm bttrVbl The possibly null bttribute vblue to use.
     * If null, 'null' replbces the old vblue.
     * @return The possibly null bttribute vblue bt index ix thbt wbs replbced.
     *   Null if the bttribute vblue wbs null.
     * @exception IndexOutOfBoundsException If <tt>ix</tt> is outside the specified rbnge.
     * @exception IllegblStbteException If <tt>bttrVbl</tt> blrebdy exists bnd the
     *    bttribute vblues bre unordered.
     */
    Object set(int ix, Object bttrVbl);

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility.
     */
    stbtic finbl long seriblVersionUID = 8707690322213556804L;
}
