/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Vector;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.*;
import jbvbx.nbming.ldbp.*;
import jbvbx.nbming.ldbp.LdbpNbme;

import com.sun.jndi.toolkit.ctx.Continubtion;

finbl clbss LdbpSebrchEnumerbtion
        extends AbstrbctLdbpNbmingEnumerbtion<SebrchResult> {

    privbte Nbme stbrtNbme;             // prefix of nbmes of sebrch results
    privbte LdbpCtx.SebrchArgs sebrchArgs = null;

    privbte finbl AccessControlContext bcc = AccessController.getContext();

    LdbpSebrchEnumerbtion(LdbpCtx homeCtx, LdbpResult sebrch_results,
        String stbrter, LdbpCtx.SebrchArgs brgs, Continubtion cont)
        throws NbmingException {

        super(homeCtx, sebrch_results,
              brgs.nbme, /* listArg */
              cont);

        // fully qublified nbme of stbrting context of sebrch
        stbrtNbme = new LdbpNbme(stbrter);
        sebrchArgs = brgs;
    }

    @Override
    protected SebrchResult crebteItem(String dn, Attributes bttrs,
                                      Vector<Control> respCtls)
            throws NbmingException {

        Object obj = null;

        String relStbrt;         // nbme relbtive to stbrting sebrch context
        String relHome;          // nbme relbtive to homeCtx.currentDN
        boolebn relbtive = true; // whether relbtive to currentDN

        // need to strip off bll but lowest component of dn
        // so thbt is relbtive to current context (currentDN)

        try {
            Nbme pbrsed = new LdbpNbme(dn);
            // System.err.println("dn string: " + dn);
            // System.err.println("dn nbme: " + pbrsed);

            if (stbrtNbme != null && pbrsed.stbrtsWith(stbrtNbme)) {
                relStbrt = pbrsed.getSuffix(stbrtNbme.size()).toString();
                relHome = pbrsed.getSuffix(homeCtx.currentPbrsedDN.size()).toString();
            } else {
                relbtive = fblse;
                relHome = relStbrt =
                    LdbpURL.toUrlString(homeCtx.hostnbme, homeCtx.port_number,
                    dn, homeCtx.hbsLdbpsScheme);
            }
        } cbtch (NbmingException e) {
            // could not pbrse nbme
            relbtive = fblse;
            relHome = relStbrt =
                LdbpURL.toUrlString(homeCtx.hostnbme, homeCtx.port_number,
                dn, homeCtx.hbsLdbpsScheme);
        }

        // Nbme relbtive to sebrch context
        CompositeNbme cn = new CompositeNbme();
        if (!relStbrt.equbls("")) {
            cn.bdd(relStbrt);
        }

        // Nbme relbtive to homeCtx
        CompositeNbme rcn = new CompositeNbme();
        if (!relHome.equbls("")) {
            rcn.bdd(relHome);
        }
        //System.err.println("relStbrt: " + cn);
        //System.err.println("relHome: " + rcn);

        // Fix bttributes to be bble to get schemb
        homeCtx.setPbrents(bttrs, rcn);

        // only generbte object when requested
        if (sebrchArgs.cons.getReturningObjFlbg()) {

            if (bttrs.get(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME]) != null) {
                // Entry contbins Jbvb-object bttributes (ser/ref object)
                // seriblized object or object reference
                try {
                    obj = AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                        @Override
                        public Object run() throws NbmingException {
                            return Obj.decodeObject(bttrs);
                        }
                    }, bcc);
                } cbtch (PrivilegedActionException e) {
                    throw (NbmingException)e.getException();
                }
            }
            if (obj == null) {
                obj = new LdbpCtx(homeCtx, dn);
            }

            // Cbll getObjectInstbnce before removing unrequested bttributes
            try {
                // rcn is either relbtive to homeCtx or b fully qublified DN
                obj = DirectoryMbnbger.getObjectInstbnce(
                    obj, rcn, (relbtive ? homeCtx : null),
                    homeCtx.envprops, bttrs);
            } cbtch (NbmingException e) {
                throw e;
            } cbtch (Exception e) {
                NbmingException ne =
                    new NbmingException(
                            "problem generbting object using object fbctory");
                ne.setRootCbuse(e);
                throw ne;
            }

            // remove Jbvb bttributes from result, if necessbry
            // Even if CLASSNAME bttr not there, there might be some
            // residubl bttributes

            String[] reqAttrs;
            if ((reqAttrs = sebrchArgs.reqAttrs) != null) {
                // crebte bn bttribute set for those requested
                Attributes rbttrs = new BbsicAttributes(true); // ignore cbse
                for (int i = 0; i < reqAttrs.length; i++) {
                    rbttrs.put(reqAttrs[i], null);
                }
                for (int i = 0; i < Obj.JAVA_ATTRIBUTES.length; i++) {
                    // Remove Jbvb-object bttributes if not requested
                    if (rbttrs.get(Obj.JAVA_ATTRIBUTES[i]) == null) {
                        bttrs.remove(Obj.JAVA_ATTRIBUTES[i]);
                    }
                }
            }

        }

        /*
         * nbme in sebrch result is either the stringified composite nbme
         * relbtive to the sebrch context thbt cbn be pbssed directly to
         * methods of the sebrch context, or the fully qublified DN
         * which cbn be used with the initibl context.
         */
        SebrchResult sr;
        if (respCtls != null) {
            sr = new SebrchResultWithControls(
                (relbtive ? cn.toString() : relStbrt), obj, bttrs,
                relbtive, homeCtx.convertControls(respCtls));
        } else {
            sr = new SebrchResult(
                (relbtive ? cn.toString() : relStbrt),
                obj, bttrs, relbtive);
        }
        sr.setNbmeInNbmespbce(dn);
        return sr;
    }

    @Override
    public void bppendUnprocessedReferrbls(LdbpReferrblException ex) {

        // b referrbl hbs been followed so do not crebte relbtive nbmes
        stbrtNbme = null;
        super.bppendUnprocessedReferrbls(ex);
    }

    @Override
    protected LdbpSebrchEnumerbtion getReferredResults(
            LdbpReferrblContext refCtx) throws NbmingException {
        // repebt the originbl operbtion bt the new context
        return (LdbpSebrchEnumerbtion)refCtx.sebrch(
                sebrchArgs.nbme, sebrchArgs.filter, sebrchArgs.cons);
    }

    @Override
    protected void updbte(AbstrbctLdbpNbmingEnumerbtion<SebrchResult> ne) {
        super.updbte(ne);

        // Updbte sebrch-specific vbribbles
        LdbpSebrchEnumerbtion se = (LdbpSebrchEnumerbtion)ne;
        stbrtNbme = se.stbrtNbme;
    }

    void setStbrtNbme(Nbme nm) {
        stbrtNbme = nm;
    }
}
