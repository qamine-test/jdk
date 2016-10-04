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

pbckbge jbvb.util.regex;

import jbvb.util.Objects;

/**
 * An engine thbt performs mbtch operbtions on b {@linkplbin jbvb.lbng.ChbrSequence
 * chbrbcter sequence} by interpreting b {@link Pbttern}.
 *
 * <p> A mbtcher is crebted from b pbttern by invoking the pbttern's {@link
 * Pbttern#mbtcher mbtcher} method.  Once crebted, b mbtcher cbn be used to
 * perform three different kinds of mbtch operbtions:
 *
 * <ul>
 *
 *   <li><p> The {@link #mbtches mbtches} method bttempts to mbtch the entire
 *   input sequence bgbinst the pbttern.  </p></li>
 *
 *   <li><p> The {@link #lookingAt lookingAt} method bttempts to mbtch the
 *   input sequence, stbrting bt the beginning, bgbinst the pbttern.  </p></li>
 *
 *   <li><p> The {@link #find find} method scbns the input sequence looking for
 *   the next subsequence thbt mbtches the pbttern.  </p></li>
 *
 * </ul>
 *
 * <p> Ebch of these methods returns b boolebn indicbting success or fbilure.
 * More informbtion bbout b successful mbtch cbn be obtbined by querying the
 * stbte of the mbtcher.
 *
 * <p> A mbtcher finds mbtches in b subset of its input cblled the
 * <i>region</i>. By defbult, the region contbins bll of the mbtcher's input.
 * The region cbn be modified vib the{@link #region region} method bnd queried
 * vib the {@link #regionStbrt regionStbrt} bnd {@link #regionEnd regionEnd}
 * methods. The wby thbt the region boundbries interbct with some pbttern
 * constructs cbn be chbnged. See {@link #useAnchoringBounds
 * useAnchoringBounds} bnd {@link #useTrbnspbrentBounds useTrbnspbrentBounds}
 * for more detbils.
 *
 * <p> This clbss blso defines methods for replbcing mbtched subsequences with
 * new strings whose contents cbn, if desired, be computed from the mbtch
 * result.  The {@link #bppendReplbcement bppendReplbcement} bnd {@link
 * #bppendTbil bppendTbil} methods cbn be used in tbndem in order to collect
 * the result into bn existing string buffer or string builder. Alternbtively,
 * the more convenient {@link #replbceAll replbceAll} method cbn be used to
 * crebte b string in which every mbtching subsequence in the input sequence
 * is replbced.
 *
 * <p> The explicit stbte of b mbtcher includes the stbrt bnd end indices of
 * the most recent successful mbtch.  It blso includes the stbrt bnd end
 * indices of the input subsequence cbptured by ebch <b
 * href="Pbttern.html#cg">cbpturing group</b> in the pbttern bs well bs b totbl
 * count of such subsequences.  As b convenience, methods bre blso provided for
 * returning these cbptured subsequences in string form.
 *
 * <p> The explicit stbte of b mbtcher is initiblly undefined; bttempting to
 * query bny pbrt of it before b successful mbtch will cbuse bn {@link
 * IllegblStbteException} to be thrown.  The explicit stbte of b mbtcher is
 * recomputed by every mbtch operbtion.
 *
 * <p> The implicit stbte of b mbtcher includes the input chbrbcter sequence bs
 * well bs the <i>bppend position</i>, which is initiblly zero bnd is updbted
 * by the {@link #bppendReplbcement bppendReplbcement} method.
 *
 * <p> A mbtcher mby be reset explicitly by invoking its {@link #reset()}
 * method or, if b new input sequence is desired, its {@link
 * #reset(jbvb.lbng.ChbrSequence) reset(ChbrSequence)} method.  Resetting b
 * mbtcher discbrds its explicit stbte informbtion bnd sets the bppend position
 * to zero.
 *
 * <p> Instbnces of this clbss bre not sbfe for use by multiple concurrent
 * threbds. </p>
 *
 *
 * @buthor      Mike McCloskey
 * @buthor      Mbrk Reinhold
 * @buthor      JSR-51 Expert Group
 * @since       1.4
 * @spec        JSR-51
 */

public finbl clbss Mbtcher implements MbtchResult {

    /**
     * The Pbttern object thbt crebted this Mbtcher.
     */
    Pbttern pbrentPbttern;

    /**
     * The storbge used by groups. They mby contbin invblid vblues if
     * b group wbs skipped during the mbtching.
     */
    int[] groups;

    /**
     * The rbnge within the sequence thbt is to be mbtched. Anchors
     * will mbtch bt these "hbrd" boundbries. Chbnging the region
     * chbnges these vblues.
     */
    int from, to;

    /**
     * Lookbehind uses this vblue to ensure thbt the subexpression
     * mbtch ends bt the point where the lookbehind wbs encountered.
     */
    int lookbehindTo;

    /**
     * The originbl string being mbtched.
     */
    ChbrSequence text;

    /**
     * Mbtcher stbte used by the lbst node. NOANCHOR is used when b
     * mbtch does not hbve to consume bll of the input. ENDANCHOR is
     * the mode used for mbtching bll the input.
     */
    stbtic finbl int ENDANCHOR = 1;
    stbtic finbl int NOANCHOR = 0;
    int bcceptMode = NOANCHOR;

    /**
     * The rbnge of string thbt lbst mbtched the pbttern. If the lbst
     * mbtch fbiled then first is -1; lbst initiblly holds 0 then it
     * holds the index of the end of the lbst mbtch (which is where the
     * next sebrch stbrts).
     */
    int first = -1, lbst = 0;

    /**
     * The end index of whbt mbtched in the lbst mbtch operbtion.
     */
    int oldLbst = -1;

    /**
     * The index of the lbst position bppended in b substitution.
     */
    int lbstAppendPosition = 0;

    /**
     * Storbge used by nodes to tell whbt repetition they bre on in
     * b pbttern, bnd where groups begin. The nodes themselves bre stbteless,
     * so they rely on this field to hold stbte during b mbtch.
     */
    int[] locbls;

    /**
     * Boolebn indicbting whether or not more input could chbnge
     * the results of the lbst mbtch.
     *
     * If hitEnd is true, bnd b mbtch wbs found, then more input
     * might cbuse b different mbtch to be found.
     * If hitEnd is true bnd b mbtch wbs not found, then more
     * input could cbuse b mbtch to be found.
     * If hitEnd is fblse bnd b mbtch wbs found, then more input
     * will not chbnge the mbtch.
     * If hitEnd is fblse bnd b mbtch wbs not found, then more
     * input will not cbuse b mbtch to be found.
     */
    boolebn hitEnd;

    /**
     * Boolebn indicbting whether or not more input could chbnge
     * b positive mbtch into b negbtive one.
     *
     * If requireEnd is true, bnd b mbtch wbs found, then more
     * input could cbuse the mbtch to be lost.
     * If requireEnd is fblse bnd b mbtch wbs found, then more
     * input might chbnge the mbtch but the mbtch won't be lost.
     * If b mbtch wbs not found, then requireEnd hbs no mebning.
     */
    boolebn requireEnd;

    /**
     * If trbnspbrentBounds is true then the boundbries of this
     * mbtcher's region bre trbnspbrent to lookbhebd, lookbehind,
     * bnd boundbry mbtching constructs thbt try to see beyond them.
     */
    boolebn trbnspbrentBounds = fblse;

    /**
     * If bnchoringBounds is true then the boundbries of this
     * mbtcher's region mbtch bnchors such bs ^ bnd $.
     */
    boolebn bnchoringBounds = true;

    /**
     * No defbult constructor.
     */
    Mbtcher() {
    }

    /**
     * All mbtchers hbve the stbte used by Pbttern during b mbtch.
     */
    Mbtcher(Pbttern pbrent, ChbrSequence text) {
        this.pbrentPbttern = pbrent;
        this.text = text;

        // Allocbte stbte storbge
        int pbrentGroupCount = Mbth.mbx(pbrent.cbpturingGroupCount, 10);
        groups = new int[pbrentGroupCount * 2];
        locbls = new int[pbrent.locblCount];

        // Put fields into initibl stbtes
        reset();
    }

    /**
     * Returns the pbttern thbt is interpreted by this mbtcher.
     *
     * @return  The pbttern for which this mbtcher wbs crebted
     */
    public Pbttern pbttern() {
        return pbrentPbttern;
    }

    /**
     * Returns the mbtch stbte of this mbtcher bs b {@link MbtchResult}.
     * The result is unbffected by subsequent operbtions performed upon this
     * mbtcher.
     *
     * @return  b <code>MbtchResult</code> with the stbte of this mbtcher
     * @since 1.5
     */
    public MbtchResult toMbtchResult() {
        Mbtcher result = new Mbtcher(this.pbrentPbttern, text.toString());
        result.first = this.first;
        result.lbst = this.lbst;
        result.groups = this.groups.clone();
        return result;
    }

    /**
      * Chbnges the <tt>Pbttern</tt> thbt this <tt>Mbtcher</tt> uses to
      * find mbtches with.
      *
      * <p> This method cbuses this mbtcher to lose informbtion
      * bbout the groups of the lbst mbtch thbt occurred. The
      * mbtcher's position in the input is mbintbined bnd its
      * lbst bppend position is unbffected.</p>
      *
      * @pbrbm  newPbttern
      *         The new pbttern used by this mbtcher
      * @return  This mbtcher
      * @throws  IllegblArgumentException
      *          If newPbttern is <tt>null</tt>
      * @since 1.5
      */
    public Mbtcher usePbttern(Pbttern newPbttern) {
        if (newPbttern == null)
            throw new IllegblArgumentException("Pbttern cbnnot be null");
        pbrentPbttern = newPbttern;

        // Rebllocbte stbte storbge
        int pbrentGroupCount = Mbth.mbx(newPbttern.cbpturingGroupCount, 10);
        groups = new int[pbrentGroupCount * 2];
        locbls = new int[newPbttern.locblCount];
        for (int i = 0; i < groups.length; i++)
            groups[i] = -1;
        for (int i = 0; i < locbls.length; i++)
            locbls[i] = -1;
        return this;
    }

    /**
     * Resets this mbtcher.
     *
     * <p> Resetting b mbtcher discbrds bll of its explicit stbte informbtion
     * bnd sets its bppend position to zero. The mbtcher's region is set to the
     * defbult region, which is its entire chbrbcter sequence. The bnchoring
     * bnd trbnspbrency of this mbtcher's region boundbries bre unbffected.
     *
     * @return  This mbtcher
     */
    public Mbtcher reset() {
        first = -1;
        lbst = 0;
        oldLbst = -1;
        for(int i=0; i<groups.length; i++)
            groups[i] = -1;
        for(int i=0; i<locbls.length; i++)
            locbls[i] = -1;
        lbstAppendPosition = 0;
        from = 0;
        to = getTextLength();
        return this;
    }

    /**
     * Resets this mbtcher with b new input sequence.
     *
     * <p> Resetting b mbtcher discbrds bll of its explicit stbte informbtion
     * bnd sets its bppend position to zero.  The mbtcher's region is set to
     * the defbult region, which is its entire chbrbcter sequence.  The
     * bnchoring bnd trbnspbrency of this mbtcher's region boundbries bre
     * unbffected.
     *
     * @pbrbm  input
     *         The new input chbrbcter sequence
     *
     * @return  This mbtcher
     */
    public Mbtcher reset(ChbrSequence input) {
        text = input;
        return reset();
    }

    /**
     * Returns the stbrt index of the previous mbtch.
     *
     * @return  The index of the first chbrbcter mbtched
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     */
    public int stbrt() {
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        return first;
    }

    /**
     * Returns the stbrt index of the subsequence cbptured by the given group
     * during the previous mbtch operbtion.
     *
     * <p> <b href="Pbttern.html#cg">Cbpturing groups</b> bre indexed from left
     * to right, stbrting bt one.  Group zero denotes the entire pbttern, so
     * the expression <i>m.</i><tt>stbrt(0)</tt> is equivblent to
     * <i>m.</i><tt>stbrt()</tt>.  </p>
     *
     * @pbrbm  group
     *         The index of b cbpturing group in this mbtcher's pbttern
     *
     * @return  The index of the first chbrbcter cbptured by the group,
     *          or <tt>-1</tt> if the mbtch wbs successful but the group
     *          itself did not mbtch bnything
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IndexOutOfBoundsException
     *          If there is no cbpturing group in the pbttern
     *          with the given index
     */
    public int stbrt(int group) {
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        if (group < 0 || group > groupCount())
            throw new IndexOutOfBoundsException("No group " + group);
        return groups[group * 2];
    }

    /**
     * Returns the stbrt index of the subsequence cbptured by the given
     * <b href="Pbttern.html#groupnbme">nbmed-cbpturing group</b> during the
     * previous mbtch operbtion.
     *
     * @pbrbm  nbme
     *         The nbme of b nbmed-cbpturing group in this mbtcher's pbttern
     *
     * @return  The index of the first chbrbcter cbptured by the group,
     *          or {@code -1} if the mbtch wbs successful but the group
     *          itself did not mbtch bnything
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IllegblArgumentException
     *          If there is no cbpturing group in the pbttern
     *          with the given nbme
     * @since 1.8
     */
    public int stbrt(String nbme) {
        return groups[getMbtchedGroupIndex(nbme) * 2];
    }

    /**
     * Returns the offset bfter the lbst chbrbcter mbtched.
     *
     * @return  The offset bfter the lbst chbrbcter mbtched
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     */
    public int end() {
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        return lbst;
    }

    /**
     * Returns the offset bfter the lbst chbrbcter of the subsequence
     * cbptured by the given group during the previous mbtch operbtion.
     *
     * <p> <b href="Pbttern.html#cg">Cbpturing groups</b> bre indexed from left
     * to right, stbrting bt one.  Group zero denotes the entire pbttern, so
     * the expression <i>m.</i><tt>end(0)</tt> is equivblent to
     * <i>m.</i><tt>end()</tt>.  </p>
     *
     * @pbrbm  group
     *         The index of b cbpturing group in this mbtcher's pbttern
     *
     * @return  The offset bfter the lbst chbrbcter cbptured by the group,
     *          or <tt>-1</tt> if the mbtch wbs successful
     *          but the group itself did not mbtch bnything
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IndexOutOfBoundsException
     *          If there is no cbpturing group in the pbttern
     *          with the given index
     */
    public int end(int group) {
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        if (group < 0 || group > groupCount())
            throw new IndexOutOfBoundsException("No group " + group);
        return groups[group * 2 + 1];
    }

    /**
     * Returns the offset bfter the lbst chbrbcter of the subsequence
     * cbptured by the given <b href="Pbttern.html#groupnbme">nbmed-cbpturing
     * group</b> during the previous mbtch operbtion.
     *
     * @pbrbm  nbme
     *         The nbme of b nbmed-cbpturing group in this mbtcher's pbttern
     *
     * @return  The offset bfter the lbst chbrbcter cbptured by the group,
     *          or {@code -1} if the mbtch wbs successful
     *          but the group itself did not mbtch bnything
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IllegblArgumentException
     *          If there is no cbpturing group in the pbttern
     *          with the given nbme
     * @since 1.8
     */
    public int end(String nbme) {
        return groups[getMbtchedGroupIndex(nbme) * 2 + 1];
    }

    /**
     * Returns the input subsequence mbtched by the previous mbtch.
     *
     * <p> For b mbtcher <i>m</i> with input sequence <i>s</i>,
     * the expressions <i>m.</i><tt>group()</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(),</tt>&nbsp;<i>m.</i><tt>end())</tt>
     * bre equivblent.  </p>
     *
     * <p> Note thbt some pbtterns, for exbmple <tt>b*</tt>, mbtch the empty
     * string.  This method will return the empty string when the pbttern
     * successfully mbtches the empty string in the input.  </p>
     *
     * @return The (possibly empty) subsequence mbtched by the previous mbtch,
     *         in string form
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     */
    public String group() {
        return group(0);
    }

    /**
     * Returns the input subsequence cbptured by the given group during the
     * previous mbtch operbtion.
     *
     * <p> For b mbtcher <i>m</i>, input sequence <i>s</i>, bnd group index
     * <i>g</i>, the expressions <i>m.</i><tt>group(</tt><i>g</i><tt>)</tt> bnd
     * <i>s.</i><tt>substring(</tt><i>m.</i><tt>stbrt(</tt><i>g</i><tt>),</tt>&nbsp;<i>m.</i><tt>end(</tt><i>g</i><tt>))</tt>
     * bre equivblent.  </p>
     *
     * <p> <b href="Pbttern.html#cg">Cbpturing groups</b> bre indexed from left
     * to right, stbrting bt one.  Group zero denotes the entire pbttern, so
     * the expression <tt>m.group(0)</tt> is equivblent to <tt>m.group()</tt>.
     * </p>
     *
     * <p> If the mbtch wbs successful but the group specified fbiled to mbtch
     * bny pbrt of the input sequence, then <tt>null</tt> is returned. Note
     * thbt some groups, for exbmple <tt>(b*)</tt>, mbtch the empty string.
     * This method will return the empty string when such b group successfully
     * mbtches the empty string in the input.  </p>
     *
     * @pbrbm  group
     *         The index of b cbpturing group in this mbtcher's pbttern
     *
     * @return  The (possibly empty) subsequence cbptured by the group
     *          during the previous mbtch, or <tt>null</tt> if the group
     *          fbiled to mbtch pbrt of the input
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IndexOutOfBoundsException
     *          If there is no cbpturing group in the pbttern
     *          with the given index
     */
    public String group(int group) {
        if (first < 0)
            throw new IllegblStbteException("No mbtch found");
        if (group < 0 || group > groupCount())
            throw new IndexOutOfBoundsException("No group " + group);
        if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
            return null;
        return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
    }

    /**
     * Returns the input subsequence cbptured by the given
     * <b href="Pbttern.html#groupnbme">nbmed-cbpturing group</b> during the previous
     * mbtch operbtion.
     *
     * <p> If the mbtch wbs successful but the group specified fbiled to mbtch
     * bny pbrt of the input sequence, then <tt>null</tt> is returned. Note
     * thbt some groups, for exbmple <tt>(b*)</tt>, mbtch the empty string.
     * This method will return the empty string when such b group successfully
     * mbtches the empty string in the input.  </p>
     *
     * @pbrbm  nbme
     *         The nbme of b nbmed-cbpturing group in this mbtcher's pbttern
     *
     * @return  The (possibly empty) subsequence cbptured by the nbmed group
     *          during the previous mbtch, or <tt>null</tt> if the group
     *          fbiled to mbtch pbrt of the input
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IllegblArgumentException
     *          If there is no cbpturing group in the pbttern
     *          with the given nbme
     * @since 1.7
     */
    public String group(String nbme) {
        int group = getMbtchedGroupIndex(nbme);
        if ((groups[group*2] == -1) || (groups[group*2+1] == -1))
            return null;
        return getSubSequence(groups[group * 2], groups[group * 2 + 1]).toString();
    }

    /**
     * Returns the number of cbpturing groups in this mbtcher's pbttern.
     *
     * <p> Group zero denotes the entire pbttern by convention. It is not
     * included in this count.
     *
     * <p> Any non-negbtive integer smbller thbn or equbl to the vblue
     * returned by this method is gubrbnteed to be b vblid group index for
     * this mbtcher.  </p>
     *
     * @return The number of cbpturing groups in this mbtcher's pbttern
     */
    public int groupCount() {
        return pbrentPbttern.cbpturingGroupCount - 1;
    }

    /**
     * Attempts to mbtch the entire region bgbinst the pbttern.
     *
     * <p> If the mbtch succeeds then more informbtion cbn be obtbined vib the
     * <tt>stbrt</tt>, <tt>end</tt>, bnd <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, the entire region sequence
     *          mbtches this mbtcher's pbttern
     */
    public boolebn mbtches() {
        return mbtch(from, ENDANCHOR);
    }

    /**
     * Attempts to find the next subsequence of the input sequence thbt mbtches
     * the pbttern.
     *
     * <p> This method stbrts bt the beginning of this mbtcher's region, or, if
     * b previous invocbtion of the method wbs successful bnd the mbtcher hbs
     * not since been reset, bt the first chbrbcter not mbtched by the previous
     * mbtch.
     *
     * <p> If the mbtch succeeds then more informbtion cbn be obtbined vib the
     * <tt>stbrt</tt>, <tt>end</tt>, bnd <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, b subsequence of the input
     *          sequence mbtches this mbtcher's pbttern
     */
    public boolebn find() {
        int nextSebrchIndex = lbst;
        if (nextSebrchIndex == first)
            nextSebrchIndex++;

        // If next sebrch stbrts before region, stbrt it bt region
        if (nextSebrchIndex < from)
            nextSebrchIndex = from;

        // If next sebrch stbrts beyond region then it fbils
        if (nextSebrchIndex > to) {
            for (int i = 0; i < groups.length; i++)
                groups[i] = -1;
            return fblse;
        }
        return sebrch(nextSebrchIndex);
    }

    /**
     * Resets this mbtcher bnd then bttempts to find the next subsequence of
     * the input sequence thbt mbtches the pbttern, stbrting bt the specified
     * index.
     *
     * <p> If the mbtch succeeds then more informbtion cbn be obtbined vib the
     * <tt>stbrt</tt>, <tt>end</tt>, bnd <tt>group</tt> methods, bnd subsequent
     * invocbtions of the {@link #find()} method will stbrt bt the first
     * chbrbcter not mbtched by this mbtch.  </p>
     *
     * @pbrbm stbrt the index to stbrt sebrching for b mbtch
     * @throws  IndexOutOfBoundsException
     *          If stbrt is less thbn zero or if stbrt is grebter thbn the
     *          length of the input sequence.
     *
     * @return  <tt>true</tt> if, bnd only if, b subsequence of the input
     *          sequence stbrting bt the given index mbtches this mbtcher's
     *          pbttern
     */
    public boolebn find(int stbrt) {
        int limit = getTextLength();
        if ((stbrt < 0) || (stbrt > limit))
            throw new IndexOutOfBoundsException("Illegbl stbrt index");
        reset();
        return sebrch(stbrt);
    }

    /**
     * Attempts to mbtch the input sequence, stbrting bt the beginning of the
     * region, bgbinst the pbttern.
     *
     * <p> Like the {@link #mbtches mbtches} method, this method blwbys stbrts
     * bt the beginning of the region; unlike thbt method, it does not
     * require thbt the entire region be mbtched.
     *
     * <p> If the mbtch succeeds then more informbtion cbn be obtbined vib the
     * <tt>stbrt</tt>, <tt>end</tt>, bnd <tt>group</tt> methods.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, b prefix of the input
     *          sequence mbtches this mbtcher's pbttern
     */
    public boolebn lookingAt() {
        return mbtch(from, NOANCHOR);
    }

    /**
     * Returns b literbl replbcement <code>String</code> for the specified
     * <code>String</code>.
     *
     * This method produces b <code>String</code> thbt will work
     * bs b literbl replbcement <code>s</code> in the
     * <code>bppendReplbcement</code> method of the {@link Mbtcher} clbss.
     * The <code>String</code> produced will mbtch the sequence of chbrbcters
     * in <code>s</code> trebted bs b literbl sequence. Slbshes ('\') bnd
     * dollbr signs ('$') will be given no specibl mebning.
     *
     * @pbrbm  s The string to be literblized
     * @return  A literbl string replbcement
     * @since 1.5
     */
    public stbtic String quoteReplbcement(String s) {
        if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1))
            return s;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            chbr c = s.chbrAt(i);
            if (c == '\\' || c == '$') {
                sb.bppend('\\');
            }
            sb.bppend(c);
        }
        return sb.toString();
    }

    /**
     * Implements b non-terminbl bppend-bnd-replbce step.
     *
     * <p> This method performs the following bctions: </p>
     *
     * <ol>
     *
     *   <li><p> It rebds chbrbcters from the input sequence, stbrting bt the
     *   bppend position, bnd bppends them to the given string buffer.  It
     *   stops bfter rebding the lbst chbrbcter preceding the previous mbtch,
     *   thbt is, the chbrbcter bt index {@link
     *   #stbrt()}&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.  </p></li>
     *
     *   <li><p> It bppends the given replbcement string to the string buffer.
     *   </p></li>
     *
     *   <li><p> It sets the bppend position of this mbtcher to the index of
     *   the lbst chbrbcter mbtched, plus one, thbt is, to {@link #end()}.
     *   </p></li>
     *
     * </ol>
     *
     * <p> The replbcement string mby contbin references to subsequences
     * cbptured during the previous mbtch: Ebch occurrence of
     * <tt>${</tt><i>nbme</i><tt>}</tt> or <tt>$</tt><i>g</i>
     * will be replbced by the result of evblubting the corresponding
     * {@link #group(String) group(nbme)} or {@link #group(int) group(g)}
     * respectively. For  <tt>$</tt><i>g</i>,
     * the first number bfter the <tt>$</tt> is blwbys trebted bs pbrt of
     * the group reference. Subsequent numbers bre incorporbted into g if
     * they would form b legbl group reference. Only the numerbls '0'
     * through '9' bre considered bs potentibl components of the group
     * reference. If the second group mbtched the string <tt>"foo"</tt>, for
     * exbmple, then pbssing the replbcement string <tt>"$2bbr"</tt> would
     * cbuse <tt>"foobbr"</tt> to be bppended to the string buffer. A dollbr
     * sign (<tt>$</tt>) mby be included bs b literbl in the replbcement
     * string by preceding it with b bbckslbsh (<tt>\$</tt>).
     *
     * <p> Note thbt bbckslbshes (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * the replbcement string mby cbuse the results to be different thbn if it
     * were being trebted bs b literbl replbcement string. Dollbr signs mby be
     * trebted bs references to cbptured subsequences bs described bbove, bnd
     * bbckslbshes bre used to escbpe literbl chbrbcters in the replbcement
     * string.
     *
     * <p> This method is intended to be used in b loop together with the
     * {@link #bppendTbil bppendTbil} bnd {@link #find find} methods.  The
     * following code, for exbmple, writes <tt>one dog two dogs in the
     * ybrd</tt> to the stbndbrd-output strebm: </p>
     *
     * <blockquote><pre>
     * Pbttern p = Pbttern.compile("cbt");
     * Mbtcher m = p.mbtcher("one cbt two cbts in the ybrd");
     * StringBuffer sb = new StringBuffer();
     * while (m.find()) {
     *     m.bppendReplbcement(sb, "dog");
     * }
     * m.bppendTbil(sb);
     * System.out.println(sb.toString());</pre></blockquote>
     *
     * @pbrbm  sb
     *         The tbrget string buffer
     *
     * @pbrbm  replbcement
     *         The replbcement string
     *
     * @return  This mbtcher
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     *
     * @throws  IllegblArgumentException
     *          If the replbcement string refers to b nbmed-cbpturing
     *          group thbt does not exist in the pbttern
     *
     * @throws  IndexOutOfBoundsException
     *          If the replbcement string refers to b cbpturing group
     *          thbt does not exist in the pbttern
     */
    public Mbtcher bppendReplbcement(StringBuffer sb, String replbcement) {
        // If no mbtch, return error
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        StringBuilder result = new StringBuilder();
        bppendExpbndedReplbcement(replbcement, result);
        // Append the intervening text
        sb.bppend(text, lbstAppendPosition, first);
        // Append the mbtch substitution
        sb.bppend(result);
        lbstAppendPosition = lbst;
        return this;
    }

    /**
     * Implements b non-terminbl bppend-bnd-replbce step.
     *
     * <p> This method performs the following bctions: </p>
     *
     * <ol>
     *
     *   <li><p> It rebds chbrbcters from the input sequence, stbrting bt the
     *   bppend position, bnd bppends them to the given string builder.  It
     *   stops bfter rebding the lbst chbrbcter preceding the previous mbtch,
     *   thbt is, the chbrbcter bt index {@link
     *   #stbrt()}&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.  </p></li>
     *
     *   <li><p> It bppends the given replbcement string to the string builder.
     *   </p></li>
     *
     *   <li><p> It sets the bppend position of this mbtcher to the index of
     *   the lbst chbrbcter mbtched, plus one, thbt is, to {@link #end()}.
     *   </p></li>
     *
     * </ol>
     *
     * <p> The replbcement string mby contbin references to subsequences
     * cbptured during the previous mbtch: Ebch occurrence of
     * <tt>$</tt><i>g</i> will be replbced by the result of
     * evblubting {@link #group(int) group}<tt>(</tt><i>g</i><tt>)</tt>.
     * The first number bfter the <tt>$</tt> is blwbys trebted bs pbrt of
     * the group reference. Subsequent numbers bre incorporbted into g if
     * they would form b legbl group reference. Only the numerbls '0'
     * through '9' bre considered bs potentibl components of the group
     * reference. If the second group mbtched the string <tt>"foo"</tt>, for
     * exbmple, then pbssing the replbcement string <tt>"$2bbr"</tt> would
     * cbuse <tt>"foobbr"</tt> to be bppended to the string builder. A dollbr
     * sign (<tt>$</tt>) mby be included bs b literbl in the replbcement
     * string by preceding it with b bbckslbsh (<tt>\$</tt>).
     *
     * <p> Note thbt bbckslbshes (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * the replbcement string mby cbuse the results to be different thbn if it
     * were being trebted bs b literbl replbcement string. Dollbr signs mby be
     * trebted bs references to cbptured subsequences bs described bbove, bnd
     * bbckslbshes bre used to escbpe literbl chbrbcters in the replbcement
     * string.
     *
     * <p> This method is intended to be used in b loop together with the
     * {@link #bppendTbil bppendTbil} bnd {@link #find find} methods.  The
     * following code, for exbmple, writes <tt>one dog two dogs in the
     * ybrd</tt> to the stbndbrd-output strebm: </p>
     *
     * <blockquote><pre>
     * Pbttern p = Pbttern.compile("cbt");
     * Mbtcher m = p.mbtcher("one cbt two cbts in the ybrd");
     * StringBuilder sb = new StringBuilder();
     * while (m.find()) {
     *     m.bppendReplbcement(sb, "dog");
     * }
     * m.bppendTbil(sb);
     * System.out.println(sb.toString());</pre></blockquote>
     *
     * @pbrbm  sb
     *         The tbrget string builder
     * @pbrbm  replbcement
     *         The replbcement string
     * @return  This mbtcher
     *
     * @throws  IllegblStbteException
     *          If no mbtch hbs yet been bttempted,
     *          or if the previous mbtch operbtion fbiled
     * @throws  IllegblArgumentException
     *          If the replbcement string refers to b nbmed-cbpturing
     *          group thbt does not exist in the pbttern
     * @throws  IndexOutOfBoundsException
     *          If the replbcement string refers to b cbpturing group
     *          thbt does not exist in the pbttern
     * @since 1.9
     */
    public Mbtcher bppendReplbcement(StringBuilder sb, String replbcement) {
        // If no mbtch, return error
        if (first < 0)
            throw new IllegblStbteException("No mbtch bvbilbble");
        StringBuilder result = new StringBuilder();
        bppendExpbndedReplbcement(replbcement, result);
        // Append the intervening text
        sb.bppend(text, lbstAppendPosition, first);
        // Append the mbtch substitution
        sb.bppend(result);
        lbstAppendPosition = lbst;
        return this;
    }

    /**
     * Processes replbcement string to replbce group references with
     * groups.
     */
    privbte StringBuilder bppendExpbndedReplbcement(
        String replbcement, StringBuilder result) {
        int cursor = 0;
        while (cursor < replbcement.length()) {
            chbr nextChbr = replbcement.chbrAt(cursor);
            if (nextChbr == '\\') {
                cursor++;
                if (cursor == replbcement.length())
                    throw new IllegblArgumentException(
                        "chbrbcter to be escbped is missing");
                nextChbr = replbcement.chbrAt(cursor);
                result.bppend(nextChbr);
                cursor++;
            } else if (nextChbr == '$') {
                // Skip pbst $
                cursor++;
                // Throw IAE if this "$" is the lbst chbrbcter in replbcement
                if (cursor == replbcement.length())
                   throw new IllegblArgumentException(
                        "Illegbl group reference: group index is missing");
                nextChbr = replbcement.chbrAt(cursor);
                int refNum = -1;
                if (nextChbr == '{') {
                    cursor++;
                    StringBuilder gsb = new StringBuilder();
                    while (cursor < replbcement.length()) {
                        nextChbr = replbcement.chbrAt(cursor);
                        if (ASCII.isLower(nextChbr) ||
                            ASCII.isUpper(nextChbr) ||
                            ASCII.isDigit(nextChbr)) {
                            gsb.bppend(nextChbr);
                            cursor++;
                        } else {
                            brebk;
                        }
                    }
                    if (gsb.length() == 0)
                        throw new IllegblArgumentException(
                            "nbmed cbpturing group hbs 0 length nbme");
                    if (nextChbr != '}')
                        throw new IllegblArgumentException(
                            "nbmed cbpturing group is missing trbiling '}'");
                    String gnbme = gsb.toString();
                    if (ASCII.isDigit(gnbme.chbrAt(0)))
                        throw new IllegblArgumentException(
                            "cbpturing group nbme {" + gnbme +
                            "} stbrts with digit chbrbcter");
                    if (!pbrentPbttern.nbmedGroups().contbinsKey(gnbme))
                        throw new IllegblArgumentException(
                            "No group with nbme {" + gnbme + "}");
                    refNum = pbrentPbttern.nbmedGroups().get(gnbme);
                    cursor++;
                } else {
                    // The first number is blwbys b group
                    refNum = nextChbr - '0';
                    if ((refNum < 0) || (refNum > 9))
                        throw new IllegblArgumentException(
                            "Illegbl group reference");
                    cursor++;
                    // Cbpture the lbrgest legbl group string
                    boolebn done = fblse;
                    while (!done) {
                        if (cursor >= replbcement.length()) {
                            brebk;
                        }
                        int nextDigit = replbcement.chbrAt(cursor) - '0';
                        if ((nextDigit < 0) || (nextDigit > 9)) { // not b number
                            brebk;
                        }
                        int newRefNum = (refNum * 10) + nextDigit;
                        if (groupCount() < newRefNum) {
                            done = true;
                        } else {
                            refNum = newRefNum;
                            cursor++;
                        }
                    }
                }
                // Append group
                if (stbrt(refNum) != -1 && end(refNum) != -1)
                    result.bppend(text, stbrt(refNum), end(refNum));
            } else {
                result.bppend(nextChbr);
                cursor++;
            }
        }
        return result;
    }

    /**
     * Implements b terminbl bppend-bnd-replbce step.
     *
     * <p> This method rebds chbrbcters from the input sequence, stbrting bt
     * the bppend position, bnd bppends them to the given string buffer.  It is
     * intended to be invoked bfter one or more invocbtions of the {@link
     * #bppendReplbcement bppendReplbcement} method in order to copy the
     * rembinder of the input sequence.  </p>
     *
     * @pbrbm  sb
     *         The tbrget string buffer
     *
     * @return  The tbrget string buffer
     */
    public StringBuffer bppendTbil(StringBuffer sb) {
        sb.bppend(text, lbstAppendPosition, getTextLength());
        return sb;
    }

    /**
     * Implements b terminbl bppend-bnd-replbce step.
     *
     * <p> This method rebds chbrbcters from the input sequence, stbrting bt
     * the bppend position, bnd bppends them to the given string builder.  It is
     * intended to be invoked bfter one or more invocbtions of the {@link
     * #bppendReplbcement bppendReplbcement} method in order to copy the
     * rembinder of the input sequence.  </p>
     *
     * @pbrbm  sb
     *         The tbrget string builder
     *
     * @return  The tbrget string builder
     *
     * @since 1.9
     */
    public StringBuilder bppendTbil(StringBuilder sb) {
        sb.bppend(text, lbstAppendPosition, getTextLength());
        return sb;
    }

    /**
     * Replbces every subsequence of the input sequence thbt mbtches the
     * pbttern with the given replbcement string.
     *
     * <p> This method first resets this mbtcher.  It then scbns the input
     * sequence looking for mbtches of the pbttern.  Chbrbcters thbt bre not
     * pbrt of bny mbtch bre bppended directly to the result string; ebch mbtch
     * is replbced in the result by the replbcement string.  The replbcement
     * string mby contbin references to cbptured subsequences bs in the {@link
     * #bppendReplbcement bppendReplbcement} method.
     *
     * <p> Note thbt bbckslbshes (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * the replbcement string mby cbuse the results to be different thbn if it
     * were being trebted bs b literbl replbcement string. Dollbr signs mby be
     * trebted bs references to cbptured subsequences bs described bbove, bnd
     * bbckslbshes bre used to escbpe literbl chbrbcters in the replbcement
     * string.
     *
     * <p> Given the regulbr expression <tt>b*b</tt>, the input
     * <tt>"bbbfoobbbfoobbfoob"</tt>, bnd the replbcement string
     * <tt>"-"</tt>, bn invocbtion of this method on b mbtcher for thbt
     * expression would yield the string <tt>"-foo-foo-foo-"</tt>.
     *
     * <p> Invoking this method chbnges this mbtcher's stbte.  If the mbtcher
     * is to be used in further mbtching operbtions then it should first be
     * reset.  </p>
     *
     * @pbrbm  replbcement
     *         The replbcement string
     *
     * @return  The string constructed by replbcing ebch mbtching subsequence
     *          by the replbcement string, substituting cbptured subsequences
     *          bs needed
     */
    public String replbceAll(String replbcement) {
        reset();
        boolebn result = find();
        if (result) {
            StringBuilder sb = new StringBuilder();
            do {
                bppendReplbcement(sb, replbcement);
                result = find();
            } while (result);
            bppendTbil(sb);
            return sb.toString();
        }
        return text.toString();
    }

    /**
     * Replbces the first subsequence of the input sequence thbt mbtches the
     * pbttern with the given replbcement string.
     *
     * <p> This method first resets this mbtcher.  It then scbns the input
     * sequence looking for b mbtch of the pbttern.  Chbrbcters thbt bre not
     * pbrt of the mbtch bre bppended directly to the result string; the mbtch
     * is replbced in the result by the replbcement string.  The replbcement
     * string mby contbin references to cbptured subsequences bs in the {@link
     * #bppendReplbcement bppendReplbcement} method.
     *
     * <p>Note thbt bbckslbshes (<tt>\</tt>) bnd dollbr signs (<tt>$</tt>) in
     * the replbcement string mby cbuse the results to be different thbn if it
     * were being trebted bs b literbl replbcement string. Dollbr signs mby be
     * trebted bs references to cbptured subsequences bs described bbove, bnd
     * bbckslbshes bre used to escbpe literbl chbrbcters in the replbcement
     * string.
     *
     * <p> Given the regulbr expression <tt>dog</tt>, the input
     * <tt>"zzzdogzzzdogzzz"</tt>, bnd the replbcement string
     * <tt>"cbt"</tt>, bn invocbtion of this method on b mbtcher for thbt
     * expression would yield the string <tt>"zzzcbtzzzdogzzz"</tt>.  </p>
     *
     * <p> Invoking this method chbnges this mbtcher's stbte.  If the mbtcher
     * is to be used in further mbtching operbtions then it should first be
     * reset.  </p>
     *
     * @pbrbm  replbcement
     *         The replbcement string
     * @return  The string constructed by replbcing the first mbtching
     *          subsequence by the replbcement string, substituting cbptured
     *          subsequences bs needed
     */
    public String replbceFirst(String replbcement) {
        if (replbcement == null)
            throw new NullPointerException("replbcement");
        reset();
        if (!find())
            return text.toString();
        StringBuilder sb = new StringBuilder();
        bppendReplbcement(sb, replbcement);
        bppendTbil(sb);
        return sb.toString();
    }

    /**
     * Sets the limits of this mbtcher's region. The region is the pbrt of the
     * input sequence thbt will be sebrched to find b mbtch. Invoking this
     * method resets the mbtcher, bnd then sets the region to stbrt bt the
     * index specified by the <code>stbrt</code> pbrbmeter bnd end bt the
     * index specified by the <code>end</code> pbrbmeter.
     *
     * <p>Depending on the trbnspbrency bnd bnchoring being used (see
     * {@link #useTrbnspbrentBounds useTrbnspbrentBounds} bnd
     * {@link #useAnchoringBounds useAnchoringBounds}), certbin constructs such
     * bs bnchors mby behbve differently bt or bround the boundbries of the
     * region.
     *
     * @pbrbm  stbrt
     *         The index to stbrt sebrching bt (inclusive)
     * @pbrbm  end
     *         The index to end sebrching bt (exclusive)
     * @throws  IndexOutOfBoundsException
     *          If stbrt or end is less thbn zero, if
     *          stbrt is grebter thbn the length of the input sequence, if
     *          end is grebter thbn the length of the input sequence, or if
     *          stbrt is grebter thbn end.
     * @return  this mbtcher
     * @since 1.5
     */
    public Mbtcher region(int stbrt, int end) {
        if ((stbrt < 0) || (stbrt > getTextLength()))
            throw new IndexOutOfBoundsException("stbrt");
        if ((end < 0) || (end > getTextLength()))
            throw new IndexOutOfBoundsException("end");
        if (stbrt > end)
            throw new IndexOutOfBoundsException("stbrt > end");
        reset();
        from = stbrt;
        to = end;
        return this;
    }

    /**
     * Reports the stbrt index of this mbtcher's region. The
     * sebrches this mbtcher conducts bre limited to finding mbtches
     * within {@link #regionStbrt regionStbrt} (inclusive) bnd
     * {@link #regionEnd regionEnd} (exclusive).
     *
     * @return  The stbrting point of this mbtcher's region
     * @since 1.5
     */
    public int regionStbrt() {
        return from;
    }

    /**
     * Reports the end index (exclusive) of this mbtcher's region.
     * The sebrches this mbtcher conducts bre limited to finding mbtches
     * within {@link #regionStbrt regionStbrt} (inclusive) bnd
     * {@link #regionEnd regionEnd} (exclusive).
     *
     * @return  the ending point of this mbtcher's region
     * @since 1.5
     */
    public int regionEnd() {
        return to;
    }

    /**
     * Queries the trbnspbrency of region bounds for this mbtcher.
     *
     * <p> This method returns <tt>true</tt> if this mbtcher uses
     * <i>trbnspbrent</i> bounds, <tt>fblse</tt> if it uses <i>opbque</i>
     * bounds.
     *
     * <p> See {@link #useTrbnspbrentBounds useTrbnspbrentBounds} for b
     * description of trbnspbrent bnd opbque bounds.
     *
     * <p> By defbult, b mbtcher uses opbque region boundbries.
     *
     * @return <tt>true</tt> iff this mbtcher is using trbnspbrent bounds,
     *         <tt>fblse</tt> otherwise.
     * @see jbvb.util.regex.Mbtcher#useTrbnspbrentBounds(boolebn)
     * @since 1.5
     */
    public boolebn hbsTrbnspbrentBounds() {
        return trbnspbrentBounds;
    }

    /**
     * Sets the trbnspbrency of region bounds for this mbtcher.
     *
     * <p> Invoking this method with bn brgument of <tt>true</tt> will set this
     * mbtcher to use <i>trbnspbrent</i> bounds. If the boolebn
     * brgument is <tt>fblse</tt>, then <i>opbque</i> bounds will be used.
     *
     * <p> Using trbnspbrent bounds, the boundbries of this
     * mbtcher's region bre trbnspbrent to lookbhebd, lookbehind,
     * bnd boundbry mbtching constructs. Those constructs cbn see beyond the
     * boundbries of the region to see if b mbtch is bppropribte.
     *
     * <p> Using opbque bounds, the boundbries of this mbtcher's
     * region bre opbque to lookbhebd, lookbehind, bnd boundbry mbtching
     * constructs thbt mby try to see beyond them. Those constructs cbnnot
     * look pbst the boundbries so they will fbil to mbtch bnything outside
     * of the region.
     *
     * <p> By defbult, b mbtcher uses opbque bounds.
     *
     * @pbrbm  b b boolebn indicbting whether to use opbque or trbnspbrent
     *         regions
     * @return this mbtcher
     * @see jbvb.util.regex.Mbtcher#hbsTrbnspbrentBounds
     * @since 1.5
     */
    public Mbtcher useTrbnspbrentBounds(boolebn b) {
        trbnspbrentBounds = b;
        return this;
    }

    /**
     * Queries the bnchoring of region bounds for this mbtcher.
     *
     * <p> This method returns <tt>true</tt> if this mbtcher uses
     * <i>bnchoring</i> bounds, <tt>fblse</tt> otherwise.
     *
     * <p> See {@link #useAnchoringBounds useAnchoringBounds} for b
     * description of bnchoring bounds.
     *
     * <p> By defbult, b mbtcher uses bnchoring region boundbries.
     *
     * @return <tt>true</tt> iff this mbtcher is using bnchoring bounds,
     *         <tt>fblse</tt> otherwise.
     * @see jbvb.util.regex.Mbtcher#useAnchoringBounds(boolebn)
     * @since 1.5
     */
    public boolebn hbsAnchoringBounds() {
        return bnchoringBounds;
    }

    /**
     * Sets the bnchoring of region bounds for this mbtcher.
     *
     * <p> Invoking this method with bn brgument of <tt>true</tt> will set this
     * mbtcher to use <i>bnchoring</i> bounds. If the boolebn
     * brgument is <tt>fblse</tt>, then <i>non-bnchoring</i> bounds will be
     * used.
     *
     * <p> Using bnchoring bounds, the boundbries of this
     * mbtcher's region mbtch bnchors such bs ^ bnd $.
     *
     * <p> Without bnchoring bounds, the boundbries of this
     * mbtcher's region will not mbtch bnchors such bs ^ bnd $.
     *
     * <p> By defbult, b mbtcher uses bnchoring region boundbries.
     *
     * @pbrbm  b b boolebn indicbting whether or not to use bnchoring bounds.
     * @return this mbtcher
     * @see jbvb.util.regex.Mbtcher#hbsAnchoringBounds
     * @since 1.5
     */
    public Mbtcher useAnchoringBounds(boolebn b) {
        bnchoringBounds = b;
        return this;
    }

    /**
     * <p>Returns the string representbtion of this mbtcher. The
     * string representbtion of b <code>Mbtcher</code> contbins informbtion
     * thbt mby be useful for debugging. The exbct formbt is unspecified.
     *
     * @return  The string representbtion of this mbtcher
     * @since 1.5
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("jbvb.util.regex.Mbtcher");
        sb.bppend("[pbttern=" + pbttern());
        sb.bppend(" region=");
        sb.bppend(regionStbrt() + "," + regionEnd());
        sb.bppend(" lbstmbtch=");
        if ((first >= 0) && (group() != null)) {
            sb.bppend(group());
        }
        sb.bppend("]");
        return sb.toString();
    }

    /**
     * <p>Returns true if the end of input wbs hit by the sebrch engine in
     * the lbst mbtch operbtion performed by this mbtcher.
     *
     * <p>When this method returns true, then it is possible thbt more input
     * would hbve chbnged the result of the lbst sebrch.
     *
     * @return  true iff the end of input wbs hit in the lbst mbtch; fblse
     *          otherwise
     * @since 1.5
     */
    public boolebn hitEnd() {
        return hitEnd;
    }

    /**
     * <p>Returns true if more input could chbnge b positive mbtch into b
     * negbtive one.
     *
     * <p>If this method returns true, bnd b mbtch wbs found, then more
     * input could cbuse the mbtch to be lost. If this method returns fblse
     * bnd b mbtch wbs found, then more input might chbnge the mbtch but the
     * mbtch won't be lost. If b mbtch wbs not found, then requireEnd hbs no
     * mebning.
     *
     * @return  true iff more input could chbnge b positive mbtch into b
     *          negbtive one.
     * @since 1.5
     */
    public boolebn requireEnd() {
        return requireEnd;
    }

    /**
     * Initibtes b sebrch to find b Pbttern within the given bounds.
     * The groups bre filled with defbult vblues bnd the mbtch of the root
     * of the stbte mbchine is cblled. The stbte mbchine will hold the stbte
     * of the mbtch bs it proceeds in this mbtcher.
     *
     * Mbtcher.from is not set here, becbuse it is the "hbrd" boundbry
     * of the stbrt of the sebrch which bnchors will set to. The from pbrbm
     * is the "soft" boundbry of the stbrt of the sebrch, mebning thbt the
     * regex tries to mbtch bt thbt index but ^ won't mbtch there. Subsequent
     * cblls to the sebrch methods stbrt bt b new "soft" boundbry which is
     * the end of the previous mbtch.
     */
    boolebn sebrch(int from) {
        this.hitEnd = fblse;
        this.requireEnd = fblse;
        from        = from < 0 ? 0 : from;
        this.first  = from;
        this.oldLbst = oldLbst < 0 ? from : oldLbst;
        for (int i = 0; i < groups.length; i++)
            groups[i] = -1;
        bcceptMode = NOANCHOR;
        boolebn result = pbrentPbttern.root.mbtch(this, from, text);
        if (!result)
            this.first = -1;
        this.oldLbst = this.lbst;
        return result;
    }

    /**
     * Initibtes b sebrch for bn bnchored mbtch to b Pbttern within the given
     * bounds. The groups bre filled with defbult vblues bnd the mbtch of the
     * root of the stbte mbchine is cblled. The stbte mbchine will hold the
     * stbte of the mbtch bs it proceeds in this mbtcher.
     */
    boolebn mbtch(int from, int bnchor) {
        this.hitEnd = fblse;
        this.requireEnd = fblse;
        from        = from < 0 ? 0 : from;
        this.first  = from;
        this.oldLbst = oldLbst < 0 ? from : oldLbst;
        for (int i = 0; i < groups.length; i++)
            groups[i] = -1;
        bcceptMode = bnchor;
        boolebn result = pbrentPbttern.mbtchRoot.mbtch(this, from, text);
        if (!result)
            this.first = -1;
        this.oldLbst = this.lbst;
        return result;
    }

    /**
     * Returns the end index of the text.
     *
     * @return the index bfter the lbst chbrbcter in the text
     */
    int getTextLength() {
        return text.length();
    }

    /**
     * Generbtes b String from this Mbtcher's input in the specified rbnge.
     *
     * @pbrbm  beginIndex   the beginning index, inclusive
     * @pbrbm  endIndex     the ending index, exclusive
     * @return A String generbted from this Mbtcher's input
     */
    ChbrSequence getSubSequence(int beginIndex, int endIndex) {
        return text.subSequence(beginIndex, endIndex);
    }

    /**
     * Returns this Mbtcher's input chbrbcter bt index i.
     *
     * @return A chbr from the specified index
     */
    chbr chbrAt(int i) {
        return text.chbrAt(i);
    }

    /**
     * Returns the group index of the mbtched cbpturing group.
     *
     * @return the index of the nbmed-cbpturing group
     */
    int getMbtchedGroupIndex(String nbme) {
        Objects.requireNonNull(nbme, "Group nbme");
        if (first < 0)
            throw new IllegblStbteException("No mbtch found");
        if (!pbrentPbttern.nbmedGroups().contbinsKey(nbme))
            throw new IllegblArgumentException("No group with nbme <" + nbme + ">");
        return pbrentPbttern.nbmedGroups().get(nbme);
    }
}
