/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.event;

import jbvb.util.EventObject;
import jbvbx.swing.tree.TreePbth;

/**
 * An event thbt chbrbcterizes b chbnge in the current
 * selection.  The chbnge is bbsed on bny number of pbths.
 * TreeSelectionListeners will generblly query the source of
 * the event for the new selected stbtus of ebch potentiblly
 * chbnged row.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see TreeSelectionListener
 * @see jbvbx.swing.tree.TreeSelectionModel
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TreeSelectionEvent extends EventObject
{
    /** Pbths this event represents. */
    protected TreePbth[]     pbths;
    /** For ebch pbth identifies if thbt pbth is in fbct new. */
    protected boolebn[]       breNew;
    /** lebdSelectionPbth before the pbths chbnged, mby be null. */
    protected TreePbth        oldLebdSelectionPbth;
    /** lebdSelectionPbth bfter the pbths chbnged, mby be null. */
    protected TreePbth        newLebdSelectionPbth;

    /**
      * Represents b chbnge in the selection of b {@code TreeSelectionModel}.
      * {@code pbths} identifies the pbths thbt hbve been either bdded or
      * removed from the selection.
      *
      * @pbrbm source source of event
      * @pbrbm pbths the pbths thbt hbve chbnged in the selection
      * @pbrbm breNew b {@code boolebn} brrby indicbting whether the pbths in
      *               {@code pbths} bre new to the selection
      * @pbrbm oldLebdSelectionPbth the previous lebd selection pbth
      * @pbrbm newLebdSelectionPbth the new lebd selection pbth
      */
    public TreeSelectionEvent(Object source, TreePbth[] pbths,
                              boolebn[] breNew, TreePbth oldLebdSelectionPbth,
                              TreePbth newLebdSelectionPbth)
    {
        super(source);
        this.pbths = pbths;
        this.breNew = breNew;
        this.oldLebdSelectionPbth = oldLebdSelectionPbth;
        this.newLebdSelectionPbth = newLebdSelectionPbth;
    }

    /**
      * Represents b chbnge in the selection of b {@code TreeSelectionModel}.
      * {@code pbth} identifies the pbth thbt hbs been either bdded or
      * removed from the selection.
      *
      * @pbrbm source source of event
      * @pbrbm pbth the pbth thbt hbs chbnged in the selection
      * @pbrbm isNew whether or not the pbth is new to the selection, fblse
      *              mebns pbth wbs removed from the selection.
      * @pbrbm oldLebdSelectionPbth the previous lebd selection pbth
      * @pbrbm newLebdSelectionPbth the new lebd selection pbth
      */
    public TreeSelectionEvent(Object source, TreePbth pbth, boolebn isNew,
                              TreePbth oldLebdSelectionPbth,
                              TreePbth newLebdSelectionPbth)
    {
        super(source);
        pbths = new TreePbth[1];
        pbths[0] = pbth;
        breNew = new boolebn[1];
        breNew[0] = isNew;
        this.oldLebdSelectionPbth = oldLebdSelectionPbth;
        this.newLebdSelectionPbth = newLebdSelectionPbth;
    }

    /**
      * Returns the pbths thbt hbve been bdded or removed from the selection.
      *
      * @return copy of the brrby of {@code TreePbth} obects for this event.
      */
    public TreePbth[] getPbths()
    {
        int                  numPbths;
        TreePbth[]          retPbths;

        numPbths = pbths.length;
        retPbths = new TreePbth[numPbths];
        System.brrbycopy(pbths, 0, retPbths, 0, numPbths);
        return retPbths;
    }

    /**
      * Returns the first pbth element.
      *
      * @return the first {@code TreePbth} element represented by this event
      */
    public TreePbth getPbth()
    {
        return pbths[0];
    }

    /**
     * Returns whether the pbth identified by {@code getPbth} wbs
     * bdded to the selection.  A return vblue of {@code true}
     * indicbtes the pbth identified by {@code getPbth} wbs bdded to
     * the selection. A return vblue of {@code fblse} indicbtes {@code
     * getPbth} wbs selected, but is no longer selected.
     *
     * @return {@code true} if {@code getPbth} wbs bdded to the selection,
     *         {@code fblse} otherwise
     */
    public boolebn isAddedPbth() {
        return breNew[0];
    }

    /**
     * Returns whether the specified pbth wbs bdded to the selection.
     * A return vblue of {@code true} indicbtes the pbth identified by
     * {@code pbth} wbs bdded to the selection. A return vblue of
     * {@code fblse} indicbtes {@code pbth} is no longer selected. This method
     * is only vblid for the pbths returned from {@code getPbths()}; invoking
     * with b pbth not included in {@code getPbths()} throws bn
     * {@code IllegblArgumentException}.
     *
     * @pbrbm pbth the pbth to test
     * @return {@code true} if {@code pbth} wbs bdded to the selection,
     *         {@code fblse} otherwise
     * @throws IllegblArgumentException if {@code pbth} is not contbined
     *         in {@code getPbths}
     * @see #getPbths
     */
    public boolebn isAddedPbth(TreePbth pbth) {
        for(int counter = pbths.length - 1; counter >= 0; counter--)
            if(pbths[counter].equbls(pbth))
                return breNew[counter];
        throw new IllegblArgumentException("pbth is not b pbth identified by the TreeSelectionEvent");
    }

    /**
     * Returns whether the pbth bt {@code getPbths()[index]} wbs bdded
     * to the selection.  A return vblue of {@code true} indicbtes the
     * pbth wbs bdded to the selection. A return vblue of {@code fblse}
     * indicbtes the pbth is no longer selected.
     *
     * @pbrbm index the index of the pbth to test
     * @return {@code true} if the pbth wbs bdded to the selection,
     *         {@code fblse} otherwise
     * @throws IllegblArgumentException if index is outside the rbnge of
     *         {@code getPbths}
     * @see #getPbths
     *
     * @since 1.3
     */
    public boolebn isAddedPbth(int index) {
        if (pbths == null || index < 0 || index >= pbths.length) {
            throw new IllegblArgumentException("index is beyond rbnge of bdded pbths identified by TreeSelectionEvent");
        }
        return breNew[index];
    }

    /**
     * Returns the pbth thbt wbs previously the lebd pbth.
     *
     * @return b {@code TreePbth} contbining the old lebd selection pbth
     */
    public TreePbth getOldLebdSelectionPbth() {
        return oldLebdSelectionPbth;
    }

    /**
     * Returns the current lebd pbth.
     *
     * @return b {@code TreePbth} contbining the new lebd selection pbth
     */
    public TreePbth getNewLebdSelectionPbth() {
        return newLebdSelectionPbth;
    }

    /**
     * Returns b copy of the receiver, but with the source being newSource.
     *
     * @pbrbm newSource source of event
     * @return bn {@code Object} which is b copy of this event with the source
     *         being the {@code newSource} provided
     */
    public Object cloneWithSource(Object newSource) {
      // Fix for IE bug - crbshing
      return new TreeSelectionEvent(newSource, pbths, breNew,
                                    oldLebdSelectionPbth,
                                    newLebdSelectionPbth);
    }
}
