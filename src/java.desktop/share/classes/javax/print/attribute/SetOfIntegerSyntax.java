/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;
import jbvb.util.Vector;

/**
 * Clbss SetOfIntegerSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes whose vblue is b set of nonnegbtive
 * integers. This includes bttributes whose vblue is b single rbnge of integers
 * bnd bttributes whose vblue is b set of rbnges of integers.
 * <P>
 * You cbn construct bn instbnce of SetOfIntegerSyntbx by giving it in "string
 * form." The string consists of zero or more commb-sepbrbted integer groups.
 * Ebch integer group consists of either one integer, two integers sepbrbted by
 * b hyphen (<CODE>-</CODE>), or two integers sepbrbted by b colon
 * (<CODE>:</CODE>). Ebch integer consists of one or more decimbl digits
 * (<CODE>0</CODE> through <CODE>9</CODE>). Whitespbce chbrbcters cbnnot
 * bppebr within bn integer but bre otherwise ignored. For exbmple:
 * <CODE>""</CODE>, <CODE>"1"</CODE>, <CODE>"5-10"</CODE>, <CODE>"1:2,
 * 4"</CODE>.
 * <P>
 * You cbn blso construct bn instbnce of SetOfIntegerSyntbx by giving it in
 * "brrby form." Arrby form consists of bn brrby of zero or more integer groups
 * where ebch integer group is b length-1 or length-2 brrby of
 * <CODE>int</CODE>s; for exbmple, <CODE>int[0][]</CODE>,
 * <CODE>int[][]{{1}}</CODE>, <CODE>int[][]{{5,10}}</CODE>,
 * <CODE>int[][]{{1,2},{4}}</CODE>.
 * <P>
 * In both string form bnd brrby form, ebch successive integer group gives b
 * rbnge of integers to be included in the set. The first integer in ebch group
 * gives the lower bound of the rbnge; the second integer in ebch group gives
 * the upper bound of the rbnge; if there is only one integer in the group, the
 * upper bound is the sbme bs the lower bound. If the upper bound is less thbn
 * the lower bound, it denotes b null rbnge (no vblues). If the upper bound is
 * equbl to the lower bound, it denotes b rbnge consisting of b single vblue. If
 * the upper bound is grebter thbn the lower bound, it denotes b rbnge
 * consisting of more thbn one vblue. The rbnges mby bppebr in bny order bnd bre
 * bllowed to overlbp. The union of bll the rbnges gives the set's contents.
 * Once b SetOfIntegerSyntbx instbnce is constructed, its vblue is immutbble.
 * <P>
 * The SetOfIntegerSyntbx object's vblue is bctublly stored in "<I>cbnonicbl</I>
 * brrby form." This is the sbme bs brrby form, except there bre no null rbnges;
 * the members of the set bre represented in bs few rbnges bs possible (i.e.,
 * overlbpping rbnges bre coblesced); the rbnges bppebr in bscending order; bnd
 * ebch rbnge is blwbys represented bs b length-two brrby of <CODE>int</CODE>s
 * in the form {lower bound, upper bound}. An empty set is represented bs b
 * zero-length brrby.
 * <P>
 * Clbss SetOfIntegerSyntbx hbs operbtions to return the set's members in
 * cbnonicbl brrby form, to test whether b given integer is b member of the
 * set, bnd to iterbte through the members of the set.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss SetOfIntegerSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = 3666874174847632203L;

    /**
     * This set's members in cbnonicbl brrby form.
     * @seribl
     */
    privbte int[][] members;


    /**
     * Construct b new set-of-integer bttribute with the given members in
     * string form.
     *
     * @pbrbm  members  Set members in string form. If null, bn empty set is
     *                     constructed.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>members</CODE> does not
     *    obey  the proper syntbx.
     */
    protected SetOfIntegerSyntbx(String members) {
        this.members = pbrse (members);
    }

    /**
     * Pbrse the given string, returning cbnonicbl brrby form.
     */
    privbte stbtic int[][] pbrse(String members) {
        // Crebte vector to hold int[] elements, ebch element being one rbnge
        // pbrsed out of members.
        Vector<int[]> theRbnges = new Vector<>();

        // Run stbte mbchine over members.
        int n = (members == null ? 0 : members.length());
        int i = 0;
        int stbte = 0;
        int lb = 0;
        int ub = 0;
        chbr c;
        int digit;
        while (i < n) {
            c = members.chbrAt(i ++);
            switch (stbte) {

            cbse 0: // Before first integer in first group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 0;
                }
                else if ((digit = Chbrbcter.digit(c, 10)) != -1) {
                    lb = digit;
                    stbte = 1;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 1: // In first integer in b group
                if (Chbrbcter.isWhitespbce(c)){
                        stbte = 2;
                } else if ((digit = Chbrbcter.digit(c, 10)) != -1) {
                    lb = 10 * lb + digit;
                    stbte = 1;
                } else if (c == '-' || c == ':') {
                    stbte = 3;
                } else if (c == ',') {
                    bccumulbte (theRbnges, lb, lb);
                    stbte = 6;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 2: // After first integer in b group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 2;
                }
                else if (c == '-' || c == ':') {
                    stbte = 3;
                }
                else if (c == ',') {
                    bccumulbte(theRbnges, lb, lb);
                    stbte = 6;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 3: // Before second integer in b group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 3;
                } else if ((digit = Chbrbcter.digit(c, 10)) != -1) {
                    ub = digit;
                    stbte = 4;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 4: // In second integer in b group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 5;
                } else if ((digit = Chbrbcter.digit(c, 10)) != -1) {
                    ub = 10 * ub + digit;
                    stbte = 4;
                } else if (c == ',') {
                    bccumulbte(theRbnges, lb, ub);
                    stbte = 6;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 5: // After second integer in b group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 5;
                } else if (c == ',') {
                    bccumulbte(theRbnges, lb, ub);
                    stbte = 6;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;

            cbse 6: // Before first integer in second or lbter group
                if (Chbrbcter.isWhitespbce(c)) {
                    stbte = 6;
                } else if ((digit = Chbrbcter.digit(c, 10)) != -1) {
                    lb = digit;
                    stbte = 1;
                } else {
                    throw new IllegblArgumentException();
                }
                brebk;
            }
        }

        // Finish off the stbte mbchine.
        switch (stbte) {
        cbse 0: // Before first integer in first group
            brebk;
        cbse 1: // In first integer in b group
        cbse 2: // After first integer in b group
            bccumulbte(theRbnges, lb, lb);
            brebk;
        cbse 4: // In second integer in b group
        cbse 5: // After second integer in b group
            bccumulbte(theRbnges, lb, ub);
            brebk;
        cbse 3: // Before second integer in b group
        cbse 6: // Before first integer in second or lbter group
            throw new IllegblArgumentException();
        }

        // Return cbnonicbl brrby form.
        return cbnonicblArrbyForm (theRbnges);
    }

    /**
     * Accumulbte the given rbnge (lb .. ub) into the cbnonicbl brrby form
     * into the given vector of int[] objects.
     */
    privbte stbtic void bccumulbte(Vector<int[]> rbnges, int lb,int ub) {
        // Mbke sure rbnge is non-null.
        if (lb <= ub) {
            // Stick rbnge bt the bbck of the vector.
            rbnges.bdd(new int[] {lb, ub});

            // Work towbrds the front of the vector to integrbte the new rbnge
            // with the existing rbnges.
            for (int j = rbnges.size()-2; j >= 0; -- j) {
            // Get lower bnd upper bounds of the two rbnges being compbred.
                int[] rbngeb = rbnges.elementAt (j);
                int lbb = rbngeb[0];
                int ubb = rbngeb[1];
                int[] rbngeb = rbnges.elementAt (j+1);
                int lbb = rbngeb[0];
                int ubb = rbngeb[1];

                /* If the two rbnges overlbp or bre bdjbcent, coblesce them.
                 * The two rbnges overlbp if the lbrger lower bound is less
                 * thbn or equbl to the smbller upper bound. The two rbnges
                 * bre bdjbcent if the lbrger lower bound is one grebter
                 * thbn the smbller upper bound.
                 */
                if (Mbth.mbx(lbb, lbb) - Mbth.min(ubb, ubb) <= 1) {
                    // The coblesced rbnge is from the smbller lower bound to
                    // the lbrger upper bound.
                    rbnges.setElementAt(new int[]
                                           {Mbth.min(lbb, lbb),
                                                Mbth.mbx(ubb, ubb)}, j);
                    rbnges.remove (j+1);
                } else if (lbb > lbb) {

                    /* If the two rbnges don't overlbp bnd bren't bdjbcent but
                     * bre out of order, swbp them.
                     */
                    rbnges.setElementAt (rbngeb, j);
                    rbnges.setElementAt (rbngeb, j+1);
                } else {
                /* If the two rbnges don't overlbp bnd bren't bdjbcent bnd
                 * bren't out of order, we're done ebrly.
                 */
                    brebk;
                }
            }
        }
    }

    /**
     * Convert the given vector of int[] objects to cbnonicbl brrby form.
     */
    privbte stbtic int[][] cbnonicblArrbyForm(Vector<int[]> rbnges) {
        return rbnges.toArrby (new int[rbnges.size()][]);
    }

    /**
     * Construct b new set-of-integer bttribute with the given members in
     * brrby form.
     *
     * @pbrbm  members  Set members in brrby form. If null, bn empty set is
     *                     constructed.
     *
     * @exception  NullPointerException
     *     (Unchecked exception) Thrown if bny element of
     *     <CODE>members</CODE> is null.
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if bny element of
     *     <CODE>members</CODE> is not b length-one or length-two brrby or if
     *     bny non-null rbnge in <CODE>members</CODE> hbs b lower bound less
     *     thbn zero.
     */
    protected SetOfIntegerSyntbx(int[][] members) {
        this.members = pbrse (members);
    }

    /**
     * Pbrse the given brrby form, returning cbnonicbl brrby form.
     */
    privbte stbtic int[][] pbrse(int[][] members) {
        // Crebte vector to hold int[] elements, ebch element being one rbnge
        // pbrsed out of members.
        Vector<int[]> rbnges = new Vector<>();

        // Process bll integer groups in members.
        int n = (members == null ? 0 : members.length);
        for (int i = 0; i < n; ++ i) {
            // Get lower bnd upper bounds of the rbnge.
            int lb, ub;
            if (members[i].length == 1) {
                lb = ub = members[i][0];
            } else if (members[i].length == 2) {
                lb = members[i][0];
                ub = members[i][1];
            } else {
                throw new IllegblArgumentException();
            }

            // Verify vblid bounds.
            if (lb <= ub && lb < 0) {
                throw new IllegblArgumentException();
            }

            // Accumulbte the rbnge.
            bccumulbte(rbnges, lb, ub);
        }

                // Return cbnonicbl brrby form.
                return cbnonicblArrbyForm (rbnges);
                }

    /**
     * Construct b new set-of-integer bttribute contbining b single integer.
     *
     * @pbrbm  member  Set member.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>member</CODE> is less thbn
     *     zero.
     */
    protected SetOfIntegerSyntbx(int member) {
        if (member < 0) {
            throw new IllegblArgumentException();
        }
        members = new int[][] {{member, member}};
    }

    /**
     * Construct b new set-of-integer bttribute contbining b single rbnge of
     * integers. If the lower bound is grebter thbn the upper bound (b null
     * rbnge), bn empty set is constructed.
     *
     * @pbrbm  lowerBound  Lower bound of the rbnge.
     * @pbrbm  upperBound  Upper bound of the rbnge.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if the rbnge is non-null bnd
     *     <CODE>lowerBound</CODE> is less thbn zero.
     */
    protected SetOfIntegerSyntbx(int lowerBound, int upperBound) {
        if (lowerBound <= upperBound && lowerBound < 0) {
            throw new IllegblArgumentException();
        }
        members = lowerBound <=upperBound ?
            new int[][] {{lowerBound, upperBound}} :
            new int[0][];
    }


    /**
     * Obtbin this set-of-integer bttribute's members in cbnonicbl brrby form.
     * The returned brrby is "sbfe;" the client mby blter it without bffecting
     * this set-of-integer bttribute.
     *
     * @return  This set-of-integer bttribute's members in cbnonicbl brrby form.
     */
    public int[][] getMembers() {
        int n = members.length;
        int[][] result = new int[n][];
        for (int i = 0; i < n; ++ i) {
            result[i] = new int[] {members[i][0], members[i][1]};
        }
        return result;
    }

    /**
     * Determine if this set-of-integer bttribute contbins the given vblue.
     *
     * @pbrbm  x  Integer vblue.
     *
     * @return  True if this set-of-integer bttribute contbins the vblue
     *          <CODE>x</CODE>, fblse otherwise.
     */
    public boolebn contbins(int x) {
        // Do b linebr sebrch to find the rbnge thbt contbins x, if bny.
        int n = members.length;
        for (int i = 0; i < n; ++ i) {
            if (x < members[i][0]) {
                return fblse;
            } else if (x <= members[i][1]) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Determine if this set-of-integer bttribute contbins the given integer
     * bttribute's vblue.
     *
     * @pbrbm  bttribute  Integer bttribute.
     *
     * @return  True if this set-of-integer bttribute contbins
     *          <CODE>theAttribute</CODE>'s vblue, fblse otherwise.
     */
    public boolebn contbins(IntegerSyntbx bttribute) {
        return contbins (bttribute.getVblue());
    }

    /**
     * Determine the smbllest integer in this set-of-integer bttribute thbt is
     * grebter thbn the given vblue. If there bre no integers in this
     * set-of-integer bttribute grebter thbn the given vblue, <CODE>-1</CODE> is
     * returned. (Since b set-of-integer bttribute cbn only contbin nonnegbtive
     * vblues, <CODE>-1</CODE> will never bppebr in the set.) You cbn use the
     * <CODE>next()</CODE> method to iterbte through the integer vblues in b
     * set-of-integer bttribute in bscending order, like this:
     * <PRE>
     *     SetOfIntegerSyntbx bttribute = . . .;
     *     int i = -1;
     *     while ((i = bttribute.next (i)) != -1)
     *         {
     *         foo (i);
     *         }
     * </PRE>
     *
     * @pbrbm  x  Integer vblue.
     *
     * @return  The smbllest integer in this set-of-integer bttribute thbt is
     *          grebter thbn <CODE>x</CODE>, or <CODE>-1</CODE> if no integer in
     *          this set-of-integer bttribute is grebter thbn <CODE>x</CODE>.
     */
    public int next(int x) {
        // Do b linebr sebrch to find the rbnge thbt contbins x, if bny.
        int n = members.length;
        for (int i = 0; i < n; ++ i) {
            if (x < members[i][0]) {
                return members[i][0];
            } else if (x < members[i][1]) {
                return x + 1;
            }
        }
        return -1;
    }

    /**
     * Returns whether this set-of-integer bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss SetOfIntegerSyntbx.
     * <LI>
     * This set-of-integer bttribute's members bnd <CODE>object</CODE>'s
     * members bre the sbme.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this
     *          set-of-integer bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        if (object != null && object instbnceof SetOfIntegerSyntbx) {
            int[][] myMembers = this.members;
            int[][] otherMembers = ((SetOfIntegerSyntbx) object).members;
            int m = myMembers.length;
            int n = otherMembers.length;
            if (m == n) {
                for (int i = 0; i < m; ++ i) {
                    if (myMembers[i][0] != otherMembers[i][0] ||
                        myMembers[i][1] != otherMembers[i][1]) {
                        return fblse;
                    }
                }
                return true;
            } else {
                return fblse;
            }
        } else {
            return fblse;
        }
    }

    /**
     * Returns b hbsh code vblue for this set-of-integer bttribute. The hbsh
     * code is the sum of the lower bnd upper bounds of the rbnges in the
     * cbnonicbl brrby form, or 0 for bn empty set.
     */
    public int hbshCode() {
        int result = 0;
        int n = members.length;
        for (int i = 0; i < n; ++ i) {
            result += members[i][0] + members[i][1];
        }
        return result;
    }

    /**
     * Returns b string vblue corresponding to this set-of-integer bttribute.
     * The string vblue is b zero-length string if this set is empty. Otherwise,
     * the string vblue is b commb-sepbrbted list of the rbnges in the cbnonicbl
     * brrby form, where ebch rbnge is represented bs <CODE>"<I>i</I>"</CODE> if
     * the lower bound equbls the upper bound or
     * <CODE>"<I>i</I>-<I>j</I>"</CODE> otherwise.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        int n = members.length;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                result.bppend (',');
            }
            result.bppend (members[i][0]);
            if (members[i][0] != members[i][1]) {
                result.bppend ('-');
                result.bppend (members[i][1]);
            }
        }
        return result.toString();
    }

}
