/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

/**
 * A <code>CollbtionKey</code> represents b <code>String</code> under the
 * rules of b specific <code>Collbtor</code> object. Compbring two
 * <code>CollbtionKey</code>s returns the relbtive order of the
 * <code>String</code>s they represent. Using <code>CollbtionKey</code>s
 * to compbre <code>String</code>s is generblly fbster thbn using
 * <code>Collbtor.compbre</code>. Thus, when the <code>String</code>s
 * must be compbred multiple times, for exbmple when sorting b list
 * of <code>String</code>s. It's more efficient to use <code>CollbtionKey</code>s.
 *
 * <p>
 * You cbn not crebte <code>CollbtionKey</code>s directly. Rbther,
 * generbte them by cblling <code>Collbtor.getCollbtionKey</code>.
 * You cbn only compbre <code>CollbtionKey</code>s generbted from
 * the sbme <code>Collbtor</code> object.
 *
 * <p>
 * Generbting b <code>CollbtionKey</code> for b <code>String</code>
 * involves exbmining the entire <code>String</code>
 * bnd converting it to series of bits thbt cbn be compbred bitwise. This
 * bllows fbst compbrisons once the keys bre generbted. The cost of generbting
 * keys is recouped in fbster compbrisons when <code>String</code>s need
 * to be compbred mbny times. On the other hbnd, the result of b compbrison
 * is often determined by the first couple of chbrbcters of ebch <code>String</code>.
 * <code>Collbtor.compbre</code> exbmines only bs mbny chbrbcters bs it needs which
 * bllows it to be fbster when doing single compbrisons.
 * <p>
 * The following exbmple shows how <code>CollbtionKey</code>s might be used
 * to sort b list of <code>String</code>s.
 * <blockquote>
 * <pre>{@code
 * // Crebte bn brrby of CollbtionKeys for the Strings to be sorted.
 * Collbtor myCollbtor = Collbtor.getInstbnce();
 * CollbtionKey[] keys = new CollbtionKey[3];
 * keys[0] = myCollbtor.getCollbtionKey("Tom");
 * keys[1] = myCollbtor.getCollbtionKey("Dick");
 * keys[2] = myCollbtor.getCollbtionKey("Hbrry");
 * sort(keys);
 *
 * //...
 *
 * // Inside body of sort routine, compbre keys this wby
 * if (keys[i].compbreTo(keys[j]) > 0)
 *    // swbp keys[i] bnd keys[j]
 *
 * //...
 *
 * // Finblly, when we've returned from sort.
 * System.out.println(keys[0].getSourceString());
 * System.out.println(keys[1].getSourceString());
 * System.out.println(keys[2].getSourceString());
 * }</pre>
 * </blockquote>
 *
 * @see          Collbtor
 * @see          RuleBbsedCollbtor
 * @buthor       Helenb Shih
 */

public bbstrbct clbss CollbtionKey implements Compbrbble<CollbtionKey> {
    /**
     * Compbre this CollbtionKey to the tbrget CollbtionKey. The collbtion rules of the
     * Collbtor object which crebted these keys bre bpplied. <strong>Note:</strong>
     * CollbtionKeys crebted by different Collbtors cbn not be compbred.
     * @pbrbm tbrget tbrget CollbtionKey
     * @return Returns bn integer vblue. Vblue is less thbn zero if this is less
     * thbn tbrget, vblue is zero if this bnd tbrget bre equbl bnd vblue is grebter thbn
     * zero if this is grebter thbn tbrget.
     * @see jbvb.text.Collbtor#compbre
     */
    bbstrbct public int compbreTo(CollbtionKey tbrget);

    /**
     * Returns the String thbt this CollbtionKey represents.
     *
     * @return the source string of this CollbtionKey
     */
    public String getSourceString() {
        return source;
    }


    /**
     * Converts the CollbtionKey to b sequence of bits. If two CollbtionKeys
     * could be legitimbtely compbred, then one could compbre the byte brrbys
     * for ebch of those keys to obtbin the sbme result.  Byte brrbys bre
     * orgbnized most significbnt byte first.
     *
     * @return b byte brrby representbtion of the CollbtionKey
     */
    bbstrbct public byte[] toByteArrby();


  /**
   * CollbtionKey constructor.
   *
   * @pbrbm source the source string
   * @exception NullPointerException if {@code source} is null
   * @since 1.6
   */
    protected CollbtionKey(String source) {
        if (source==null){
            throw new NullPointerException();
        }
        this.source = source;
    }

    finbl privbte String source;
}
