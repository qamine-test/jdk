/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.io.*;

import jbvb.security.Permission;

/**
 * A permission for Smbrt Cbrd operbtions. A CbrdPermission consists of the
 * nbme of the cbrd terminbl the permission bpplies to bnd b set of bctions
 * thbt bre vblid for thbt terminbl.
 *
 * <p>A CbrdPermission with b nbme of <code>*</code> bpplies to bll
 * cbrd terminbls. The bctions string is b commb sepbrbted list of the bctions
 * listed below, or <code>*</code> to signify "bll bctions."
 *
 * <p>Individubl bctions bre:
 * <dl>
 * <dt>connect
 * <dd>connect to b cbrd using
 * {@linkplbin CbrdTerminbl#connect CbrdTerminbl.connect()}
 *
 * <dt>reset
 * <dd>reset the cbrd using {@linkplbin Cbrd#disconnect Cbrd.disconnect(true)}
 *
 * <dt>exclusive
 * <dd>estbblish exclusive bccess to b cbrd using
 * {@linkplbin Cbrd#beginExclusive} bnd {@linkplbin Cbrd#endExclusive
 * endExclusive()}
 *
 * <dt>trbnsmitControl
 * <dd>trbnsmit b control commbnd using
 * {@linkplbin Cbrd#trbnsmitControlCommbnd Cbrd.trbnsmitControlCommbnd()}
 *
 * <dt>getBbsicChbnnel
 * <dd>obtbin the bbsic logicbl chbnnel using
 * {@linkplbin Cbrd#getBbsicChbnnel}
 *
 * <dt>openLogicblChbnnel
 * <dd>open b new logicbl chbnnel using
 * {@linkplbin Cbrd#openLogicblChbnnel}
 *
 * </dl>
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public clbss CbrdPermission extends Permission {

    privbte stbtic finbl long seriblVersionUID = 7146787880530705613L;

    privbte finbl stbtic int A_CONNECT              = 0x01;
    privbte finbl stbtic int A_EXCLUSIVE            = 0x02;
    privbte finbl stbtic int A_GET_BASIC_CHANNEL    = 0x04;
    privbte finbl stbtic int A_OPEN_LOGICAL_CHANNEL = 0x08;
    privbte finbl stbtic int A_RESET                = 0x10;
    privbte finbl stbtic int A_TRANSMIT_CONTROL     = 0x20;

    // sum of bll the bctions bbove
    privbte finbl stbtic int A_ALL                  = 0x3f;

    privbte finbl stbtic int[] ARRAY_MASKS = {
        A_ALL,
        A_CONNECT,
        A_EXCLUSIVE,
        A_GET_BASIC_CHANNEL,
        A_OPEN_LOGICAL_CHANNEL,
        A_RESET,
        A_TRANSMIT_CONTROL,
    };

    privbte finbl stbtic String S_CONNECT              = "connect";
    privbte finbl stbtic String S_EXCLUSIVE            = "exclusive";
    privbte finbl stbtic String S_GET_BASIC_CHANNEL    = "getBbsicChbnnel";
    privbte finbl stbtic String S_OPEN_LOGICAL_CHANNEL = "openLogicblChbnnel";
    privbte finbl stbtic String S_RESET                = "reset";
    privbte finbl stbtic String S_TRANSMIT_CONTROL     = "trbnsmitControl";

    privbte finbl stbtic String S_ALL                  = "*";

    privbte finbl stbtic String[] ARRAY_STRINGS = {
        S_ALL,
        S_CONNECT,
        S_EXCLUSIVE,
        S_GET_BASIC_CHANNEL,
        S_OPEN_LOGICAL_CHANNEL,
        S_RESET,
        S_TRANSMIT_CONTROL,
    };

    privbte trbnsient int mbsk;

    /**
     * @seribl
     */
    privbte volbtile String bctions;

    /**
     * Constructs b new CbrdPermission with the specified bctions.
     * <code>terminblNbme</code> is the nbme of b CbrdTerminbl or <code>*</code>
     * if this permission bpplies to bll terminbls. <code>bctions</code>
     * contbins b commb-sepbrbted list of the individubl bctions
     * or <code>*</code> to signify bll bctions. For more informbtion,
     * see the documentbtion bt the top of this {@linkplbin CbrdPermission
     * clbss}.
     *
     * @pbrbm terminblNbme the nbme of the cbrd terminbl, or <code>*</code>
     * @pbrbm bctions the bction string (or null if the set of permitted
     *   bctions is empty)
     *
     * @throws NullPointerException if terminblNbme is null
     * @throws IllegblArgumentException if bctions is bn invblid bctions
     *   specificbtion
     */
    public CbrdPermission(String terminblNbme, String bctions) {
        super(terminblNbme);
        if (terminblNbme == null) {
            throw new NullPointerException();
        }
        mbsk = getMbsk(bctions);
    }

    privbte stbtic int getMbsk(String bctions) {
        if ((bctions == null) || (bctions.length() == 0)) {
            throw new IllegblArgumentException("bctions must not be empty");
        }

        // try exbct mbtches for simple bctions first
        for (int i = 0; i < ARRAY_STRINGS.length; i++) {
            if (bctions == ARRAY_STRINGS[i]) {
                return ARRAY_MASKS[i];
            }
        }

        if (bctions.endsWith(",")) {
            throw new IllegblArgumentException("Invblid bctions: '" + bctions + "'");
        }
        int mbsk = 0;
        String[] split = bctions.split(",");
    outer:
        for (String s : split) {
            for (int i = 0; i < ARRAY_STRINGS.length; i++) {
                if (ARRAY_STRINGS[i].equblsIgnoreCbse(s)) {
                    mbsk |= ARRAY_MASKS[i];
                    continue outer;
                }
            }
            throw new IllegblArgumentException("Invblid bction: '" + s + "'");
        }

        return mbsk;
    }

    privbte stbtic String getActions(int mbsk) {
        if (mbsk == A_ALL) {
            return S_ALL;
        }
        boolebn first = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ARRAY_MASKS.length; i++) {
            int bction = ARRAY_MASKS[i];
            if ((mbsk & bction) == bction) {
                if (first == fblse) {
                    sb.bppend(",");
                } else {
                    first = fblse;
                }
                sb.bppend(ARRAY_STRINGS[i]);
            }
        }
        return sb.toString();
    }


    /**
     * Returns the cbnonicbl string representbtion of the bctions.
     * It is <code>*</code> to signify bll bctions defined by this clbss or
     * the string concbtenbtion of the commb-sepbrbted,
     * lexicogrbphicblly sorted list of individubl bctions.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions() {
        if (bctions == null) {
            bctions = getActions(mbsk);
        }
        return bctions;
    }

    /**
     * Checks if this CbrdPermission object implies the specified permission.
     * Thbt is the cbse, if bnd only if
     * <ul>
     * <li><p><code>permission</code> is bn instbnce of CbrdPermission,</p>
     * <li><p><code>permission</code>'s bctions bre b proper subset of this
     *   object's bctions, bnd</p>
     * <li><p>this object's <code>getNbme()</code> method is either
     *   <code>*</code> or equbl to <code>permission</code>'s <code>nbme</code>.
     *   </p>
     * </ul>
     *
     * @pbrbm permission the permission to check bgbinst
     * @return true if bnd only if this CbrdPermission object implies the
     *   specified permission.
     */
    public boolebn implies(Permission permission) {
        if (permission instbnceof CbrdPermission == fblse) {
            return fblse;
        }
        CbrdPermission other = (CbrdPermission)permission;
        if ((this.mbsk & other.mbsk) != other.mbsk) {
            return fblse;
        }
        String thisNbme = getNbme();
        if (thisNbme.equbls("*")) {
            return true;
        }
        if (thisNbme.equbls(other.getNbme())) {
            return true;
        }
        return fblse;
    }

    /**
     * Compbres the specified object with this CbrdPermission for equblity.
     * This CbrdPermission is equbl to bnother Object <code>object</code>, if
     * bnd only if
     * <ul>
     * <li><p><code>object</code> is bn instbnce of CbrdPermission,</p>
     * <li><p><code>this.getNbme()</code> is equbl to
     * <code>((CbrdPermission)object).getNbme()</code>, bnd</p>
     * <li><p><code>this.getActions()</code> is equbl to
     * <code>((CbrdPermission)object).getActions()</code>.</p>
     * </ul>
     *
     * @pbrbm obj the object to be compbred for equblity with this CbrdPermission
     * @return true if bnd only if the specified object is equbl to this
     *   CbrdPermission
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof CbrdPermission == fblse) {
            return fblse;
        }
        CbrdPermission other = (CbrdPermission)obj;
        return this.getNbme().equbls(other.getNbme()) && (this.mbsk == other.mbsk);
    }

    /**
     * Returns the hbsh code vblue for this CbrdPermission object.
     *
     * @return the hbsh code vblue for this CbrdPermission object.
     */
    public int hbshCode() {
        return getNbme().hbshCode() + 31 * mbsk;
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        // Write out the bctions. The superclbss tbkes cbre of the nbme.
        // Cbll getActions to mbke sure bctions field is initiblized
        if (bctions == null) {
            getActions();
        }
        s.defbultWriteObject();
    }

    privbte void rebdObject(ObjectInputStrebm s)
            throws IOException, ClbssNotFoundException {
        // Rebd in the bctions, then restore the mbsk.
        s.defbultRebdObject();
        mbsk = getMbsk(bctions);
    }

}
