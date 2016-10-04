/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.snmp.SnmpOid;

import jbvb.io.Seriblizbble;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.TreeMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.WebkReference;

/**
 * This bbstrbct clbss implements b webk cbche thbt holds tbble dbtb.
 * <p>The tbble dbtb is stored in bn instbnce of
 * {@link SnmpCbchedDbtb}, which is kept in b {@link WebkReference}.
 * If the WebkReference is null or empty, the cbched dbtb is recomputed.</p>
 *
 * <p><b>NOTE: This clbss is not synchronized, subclbsses must implement
 *          the bppropribte synchronizbtion when needed.</b></p>
 **/
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpTbbleCbche implements Seriblizbble {

    /**
     * Intervbl of time in ms during which the cbched tbble dbtb
     * is considered vblid.
     **/
    protected long vblidity;

    /**
     * A webk refernce holding cbched tbble dbtb.
     **/
    protected trbnsient WebkReference<SnmpCbchedDbtb> dbtbs;

    /**
     * true if the given cbched tbble dbtb is obsolete.
     **/
    protected boolebn isObsolete(SnmpCbchedDbtb cbched) {
        if (cbched   == null) return true;
        if (vblidity < 0)     return fblse;
        return ((System.currentTimeMillis() - cbched.lbstUpdbted) > vblidity);
    }

    /**
     * Returns the cbched tbble dbtb.
     * Returns null if the cbched dbtb is obsolete, or if there is no
     * cbched dbtb, or if the cbched dbtb wbs gbrbbge collected.
     * @return b still vblid cbched dbtb or null.
     **/
    protected SnmpCbchedDbtb getCbchedDbtbs() {
        if (dbtbs == null) return null;
        finbl SnmpCbchedDbtb cbched = dbtbs.get();
        if ((cbched == null) || isObsolete(cbched)) return null;
        return cbched;
    }

    /**
     * Returns the cbched tbble dbtb, if it is still vblid,
     * or recompute it if it is obsolete.
     * <p>
     * When cbche dbtb is recomputed, store it in the webk reference,
     * unless {@link #vblidity} is 0: then the dbtb will not be stored
     * bt bll.<br>
     * This method cblls {@link #isObsolete(SnmpCbchedDbtb)} to determine
     * whether the cbched dbtb is obsolete, bnd {
     * {@link #updbteCbchedDbtbs(Object)} to recompute it.
     * </p>
     * @pbrbm context A context object.
     * @return the vblid cbched dbtb, or the recomputed tbble dbtb.
     **/
    protected synchronized SnmpCbchedDbtb getTbbleDbtbs(Object context) {
        finbl SnmpCbchedDbtb cbched   = getCbchedDbtbs();
        if (cbched != null) return cbched;
        finbl SnmpCbchedDbtb computedDbtbs = updbteCbchedDbtbs(context);
        if (vblidity != 0) dbtbs = new WebkReference<>(computedDbtbs);
        return computedDbtbs;
    }

    /**
     * Recompute cbched dbtb.
     * @pbrbm context A context object, bs pbssed to
     *        {@link #getTbbleDbtbs(Object)}
     **/
    protected bbstrbct SnmpCbchedDbtb updbteCbchedDbtbs(Object context);

    /**
     * Return b tbble hbndler thbt holds the tbble dbtb.
     * This method should return the cbched tbble dbtb if it is still
     * vblid, recompute it bnd cbche the new vblue if it's not.
     **/
    public bbstrbct SnmpTbbleHbndler getTbbleHbndler();

}
