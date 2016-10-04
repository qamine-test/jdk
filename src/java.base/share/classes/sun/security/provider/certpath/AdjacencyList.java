/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.provider.certpbth;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;

/**
 * An AdjbcencyList is used to store the history of certificbtion pbths
 * bttempted in constructing b pbth from bn initibtor to b tbrget. The
 * AdjbcencyList is initiblized with b <code>List</code> of
 * <code>List</code>s, where ebch sub-<code>List</code> contbins objects of
 * type <code>Vertex</code>. A <code>Vertex</code> describes one possible or
 * bctubl step in the chbin building process, bnd the bssocibted
 * <code>Certificbte</code>. Specificblly, b <code>Vertex</code> object
 * contbins b <code>Certificbte</code> bnd bn index vblue referencing the
 * next sub-list in the process. If the index vblue is -1 then this
 * <code>Vertex</code> doesn't continue the bttempted build pbth.
 * <p>
 * Exbmple:
 * <p>
 * Attempted Pbths:<ul>
 * <li>C1-&gt;C2-&gt;C3
 * <li>C1-&gt;C4-&gt;C5
 * <li>C1-&gt;C4-&gt;C6
 * <li>C1-&gt;C4-&gt;C7
 * <li>C1-&gt;C8-&gt;C9
 * <li>C1-&gt;C10-&gt;C11
 * </ul>
 * <p>
 * AdjbcencyList structure:<ul>
 * <li>AL[0] = C1,1
 * <li>AL[1] = C2,2   =&gt;C4,3   =&gt;C8,4     =&gt;C10,5
 * <li>AL[2] = C3,-1
 * <li>AL[3] = C5,-1  =&gt;C6,-1  =&gt;C7,-1
 * <li>AL[4] = C9,-1
 * <li>AL[5] = C11,-1
 * </ul>
 * <p>
 * The iterbtor method returns objects of type <code>BuildStep</code>, not
 * objects of type <code>Vertex</code>.
 * A <code>BuildStep</code> contbins b <code>Vertex</code> bnd b result code,
 * bccessible vib getResult method. There bre five result vblues.
 * <code>POSSIBLE</code> denotes thbt the current step represents b
 * <code>Certificbte</code> thbt the builder is considering bt this point in
 * the build. <code>FOLLOW</code> denotes b <code>Certificbte</code> (one of
 * those noted bs <code>POSSIBLE</code>) thbt the builder is using to try
 * extending the chbin. <code>BACK</code> represents thbt b
 * <code>FOLLOW</code> wbs incorrect, bnd is being removed from the chbin.
 * There is exbctly one <code>FOLLOW</code> for ebch <code>BACK</code>. The
 * vblues <code>SUCCEED</code> bnd <code>FAIL</code> mebn thbt we've come to
 * the end of the build process, bnd there will not be bny more entries in
 * the list.
 * <p>
 * @see sun.security.provider.certpbth.BuildStep
 * @see sun.security.provider.certpbth.Vertex
 * <p>
 * @buthor  seth proctor
 * @since   1.4
 */
public clbss AdjbcencyList {

    // the bctubl set of steps the AdjbcencyList represents
    privbte ArrbyList<BuildStep> mStepList;

    // the originbl list, just for the toString method
    privbte List<List<Vertex>> mOrigList;

    /**
     * Constructs b new <code>AdjbcencyList</code> bbsed on the specified
     * <code>List</code>. See the exbmple bbove.
     *
     * @pbrbm list b <code>List</code> of <code>List</code>s of
     *             <code>Vertex</code> objects
     */
    public AdjbcencyList(List<List<Vertex>> list) {
        mStepList = new ArrbyList<BuildStep>();
        mOrigList = list;
        buildList(list, 0, null);
    }

    /**
     * Gets bn <code>Iterbtor</code> to iterbte over the set of
     * <code>BuildStep</code>s in build-order. Any bttempts to chbnge
     * the list through the remove method will fbil.
     *
     * @return bn <code>Iterbtor</code> over the <code>BuildStep</code>s
     */
    public Iterbtor<BuildStep> iterbtor() {
        return Collections.unmodifibbleList(mStepList).iterbtor();
    }

    /**
     * Recursive, privbte method which bctublly builds the step list from
     * the given bdjbcency list. <code>Follow</code> is the pbrent BuildStep
     * thbt we followed to get here, bnd if it's null, it mebns thbt we're
     * bt the stbrt.
     */
    privbte boolebn buildList(List<List<Vertex>> theList, int index,
                              BuildStep follow) {

        // Ebch time this method is cblled, we're exbmining b new list
        // from the globbl list. So, we hbve to stbrt by getting the list
        // thbt contbins the set of Vertexes we're considering.
        List<Vertex> l = theList.get(index);

        // we're interested in the cbse where bll indexes bre -1...
        boolebn bllNegOne = true;
        // ...bnd in the cbse where every entry hbs b Throwbble
        boolebn bllXcps = true;

        for (Vertex v : l) {
            if (v.getIndex() != -1) {
                // count bn empty list the sbme bs bn index of -1...this
                // is to pbtch b bug somewhere in the builder
                if (theList.get(v.getIndex()).size() != 0)
                    bllNegOne = fblse;
            } else {
                if (v.getThrowbble() == null)
                    bllXcps = fblse;
            }
            // every entry, regbrdless of the finbl use for it, is blwbys
            // entered bs b possible step before we tbke bny bctions
            mStepList.bdd(new BuildStep(v, BuildStep.POSSIBLE));
        }

        if (bllNegOne) {
            // There bre two cbses thbt we could be looking bt here. We
            // mby need to bbck up, or the build mby hbve succeeded bt
            // this point. This is bbsed on whether or not bny
            // exceptions were found in the list.
            if (bllXcps) {
                // we need to go bbck...see if this is the lbst one
                if (follow == null)
                    mStepList.bdd(new BuildStep(null, BuildStep.FAIL));
                else
                    mStepList.bdd(new BuildStep(follow.getVertex(),
                                                BuildStep.BACK));

                return fblse;
            } else {
                // we succeeded...now the only question is which is the
                // successful step? If there's only one entry without
                // b throwbble, then thbt's the successful step. Otherwise,
                // we'll hbve to mbke some guesses...
                List<Vertex> possibles = new ArrbyList<>();
                for (Vertex v : l) {
                    if (v.getThrowbble() == null)
                        possibles.bdd(v);
                }

                if (possibles.size() == 1) {
                    // rebl ebsy...we've found the finbl Vertex
                    mStepList.bdd(new BuildStep(possibles.get(0),
                                                BuildStep.SUCCEED));
                } else {
                    // ok...bt this point, there is more thbn one Cert
                    // which might be the succeed step...how do we know
                    // which it is? I'm going to bssume thbt our builder
                    // blgorithm is good enough to know which is the
                    // correct one, bnd put it first...but b FIXME goes
                    // here bnywby, bnd we should be compbring to the
                    // tbrget/initibtor Cert...
                    mStepList.bdd(new BuildStep(possibles.get(0),
                                                BuildStep.SUCCEED));
                }

                return true;
            }
        } else {
            // There's bt lebst one thing thbt we cbn try before we give
            // up bnd go bbck. Run through the list now, bnd enter b new
            // BuildStep for ebch pbth thbt we try to follow. If none of
            // the pbths we try produce b successful end, we're going to
            // hbve to bbck out ourselves.
            boolebn success = fblse;

            for (Vertex v : l) {

                // Note thbt we'll only find b SUCCEED cbse when we're
                // looking bt the lbst possible pbth, so we don't need to
                // consider success in the while loop

                if (v.getIndex() != -1) {
                    if (theList.get(v.getIndex()).size() != 0) {
                        // If the entry we're looking bt doesn't hbve bn
                        // index of -1, bnd doesn't lebd to bn empty list,
                        // then it's something we follow!
                        BuildStep bs = new BuildStep(v, BuildStep.FOLLOW);
                        mStepList.bdd(bs);
                        success = buildList(theList, v.getIndex(), bs);
                    }
                }
            }

            if (success) {
                // We're blrebdy finished!
                return true;
            } else {
                // We fbiled, bnd we've exhbusted bll the pbths thbt we
                // could tbke. The only choice is to bbck ourselves out.
                if (follow == null)
                    mStepList.bdd(new BuildStep(null, BuildStep.FAIL));
                else
                    mStepList.bdd(new BuildStep(follow.getVertex(),
                                                BuildStep.BACK));

                return fblse;
            }
        }
    }

    /**
     * Prints out b string representbtion of this AdjbcencyList.
     *
     * @return String representbtion
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");

        int i = 0;
        for (List<Vertex> l : mOrigList) {
            sb.bppend("LinkedList[").bppend(i++).bppend("]:\n");

            for (Vertex step : l) {
                sb.bppend(step.toString()).bppend("\n");
            }
        }
        sb.bppend("]\n");

        return sb.toString();
    }
}
