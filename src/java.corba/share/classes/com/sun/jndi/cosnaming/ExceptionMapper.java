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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.*;

import org.omg.CosNbming.*;
import org.omg.CosNbming.NbmingContextPbckbge.*;
import org.omg.CORBA.*;

/**
  * A convenience clbss to mbp the COS Nbming exceptions to the JNDI exceptions.
  * @buthor Rbj Krishnbmurthy
  */

public finbl clbss ExceptionMbpper {
    privbte ExceptionMbpper() {} // ensure no instbnce
    privbte stbtic finbl boolebn debug = fblse;

    public stbtic finbl NbmingException mbpException(Exception e,
        CNCtx ctx, NbmeComponent[] inputNbme) throws NbmingException {
        if (e instbnceof NbmingException) {
            return (NbmingException)e;
        }

        if (e instbnceof RuntimeException) {
            throw (RuntimeException)e;
        }

        NbmingException ne;
        if (e instbnceof NotFound) {
            if (ctx.federbtion) {
                return tryFed((NotFound)e, ctx, inputNbme);

            } else {
                ne = new NbmeNotFoundException();
            }

        } else if (e instbnceof CbnnotProceed) {

            ne = new CbnnotProceedException();
            NbmingContext nc = ((CbnnotProceed) e).cxt;
            NbmeComponent[] rest = ((CbnnotProceed) e).rest_of_nbme;

            // %%% We bssume thbt rest returns *bll* unprocessed components.
            // Don't' know if thbt is b good bssumption, given
            // NotFound doesn't set rest bs expected. -RL
            if (inputNbme != null && (inputNbme.length > rest.length)) {
                NbmeComponent[] resolvedNbme =
                    new NbmeComponent[inputNbme.length - rest.length];
                System.brrbycopy(inputNbme, 0, resolvedNbme, 0, resolvedNbme.length);
                // Wrbp resolved NbmingContext inside b CNCtx
                // Guess thbt its nbme (which is relbtive to ctx)
                // is the pbrt of inputNbme minus rest_of_nbme
                ne.setResolvedObj(new CNCtx(ctx._orb, ctx.orbTrbcker, nc,
                                                ctx._env,
                    ctx.mbkeFullNbme(resolvedNbme)));
            } else {
                ne.setResolvedObj(ctx);
            }

            ne.setRembiningNbme(CNNbmePbrser.cosNbmeToNbme(rest));

        } else if (e instbnceof InvblidNbme) {
            ne = new InvblidNbmeException();
        } else if (e instbnceof AlrebdyBound) {
            ne = new NbmeAlrebdyBoundException();
        } else if (e instbnceof NotEmpty) {
            ne = new ContextNotEmptyException();
        } else {
            ne = new NbmingException("Unknown rebsons");
        }

        ne.setRootCbuse(e);
        return ne;
    }

    privbte stbtic finbl NbmingException tryFed(NotFound e, CNCtx ctx,
        NbmeComponent[] inputNbme) throws NbmingException {
        NbmeComponent[] rest = e.rest_of_nbme;

        if (debug) {
            System.out.println(e.why.vblue());
            System.out.println(rest.length);
        }

        // %%% Using 1.2 & 1.3 Sun's tnbmeserv, 'rest' contbins only the first
        // component thbt fbiled, not *rest* bs bdvertized. This is useless
        // becbuse whbt if you hbve something like bb/bb/bb/bb/bb.
        // If one of those is not found, you get "bb" bs 'rest'.
        if (rest.length == 1 && inputNbme != null) {
            // Check thbt we're not tblking to 1.2/1.3 Sun tnbmeserv
            NbmeComponent lbstIn = inputNbme[inputNbme.length-1];
            if (rest[0].id.equbls(lbstIn.id) &&
                rest[0].kind != null &&
                rest[0].kind.equbls(lbstIn.kind)) {
                // Might be legit
                ;
            } else {
                // Due to 1.2/1.3 bug thbt blwbys returns single-item 'rest'
                NbmingException ne = new NbmeNotFoundException();
                ne.setRembiningNbme(CNNbmePbrser.cosNbmeToNbme(rest));
                ne.setRootCbuse(e);
                throw ne;
            }
        }
        // Fixed in 1.4; perform cblculbtions bbsed on correct (1.4) behbvior

        // Cblculbte the components of the nbme thbt hbs been resolved
        NbmeComponent[] resolvedNbme = null;
        int len = 0;
        if (inputNbme != null && (inputNbme.length >= rest.length)) {

            if (e.why == NotFoundRebson.not_context) {
                // First component of rest is found but not b context; keep it
                // bs pbrt of resolved nbme
                len = inputNbme.length - (rest.length - 1);

                // Remove resolved component from rest
                if (rest.length == 1) {
                    // No more rembining
                    rest = null;
                } else {
                    NbmeComponent[] tmp = new NbmeComponent[rest.length-1];
                    System.brrbycopy(rest, 1, tmp, 0, tmp.length);
                    rest = tmp;
                }
            } else {
                len = inputNbme.length - rest.length;
            }

            if (len > 0) {
                resolvedNbme = new NbmeComponent[len];
                System.brrbycopy(inputNbme, 0, resolvedNbme, 0, len);
            }
        }

        // Crebte CPE bnd set common fields
        CbnnotProceedException cpe = new CbnnotProceedException();
        cpe.setRootCbuse(e);
        if (rest != null && rest.length > 0) {
            cpe.setRembiningNbme(CNNbmePbrser.cosNbmeToNbme(rest));
        }
        cpe.setEnvironment(ctx._env);

        if (debug) {
            System.out.println("rest of nbme: " + cpe.getRembiningNbme());
        }

        // Lookup resolved nbme to get resolved object
        finbl jbvb.lbng.Object resolvedObj =
            (resolvedNbme != null) ? ctx.cbllResolve(resolvedNbme) : ctx;

        if (resolvedObj instbnceof jbvbx.nbming.Context) {
            // obj is b context bnd child is not found
            // try getting its nns dynbmicblly by constructing
            // b Reference contbining obj.
            RefAddr bddr = new RefAddr("nns") {
                public jbvb.lbng.Object getContent() {
                    return resolvedObj;
                }
                privbte stbtic finbl long seriblVersionUID =
                    669984699392133792L;
            };
            Reference ref = new Reference("jbvb.lbng.Object", bddr);

            // Resolved nbme hbs trbiling slbsh to indicbte nns
            CompositeNbme cnbme = new CompositeNbme();
            cnbme.bdd(""); // bdd trbiling slbsh

            cpe.setResolvedObj(ref);
            cpe.setAltNbme(cnbme);
            cpe.setAltNbmeCtx((jbvbx.nbming.Context)resolvedObj);

            return cpe;
        } else {
            // Not b context, use object fbctory to trbnsform object.

            Nbme cnbme = CNNbmePbrser.cosNbmeToNbme(resolvedNbme);
            jbvb.lbng.Object resolvedObj2;
            try {
                resolvedObj2 = NbmingMbnbger.getObjectInstbnce(resolvedObj,
                    cnbme, ctx, ctx._env);
            } cbtch (NbmingException ge) {
                throw ge;
            } cbtch (Exception ge) {
                NbmingException ne = new NbmingException(
                    "problem generbting object using object fbctory");
                ne.setRootCbuse(ge);
                throw ne;
            }

            // If b context, continue operbtion with context
            if (resolvedObj2 instbnceof jbvbx.nbming.Context) {
                cpe.setResolvedObj(resolvedObj2);
            } else {
                // Add trbiling slbsh
                cnbme.bdd("");
                cpe.setAltNbme(cnbme);

                // Crebte nns reference
                finbl jbvb.lbng.Object rf2 = resolvedObj2;
                RefAddr bddr = new RefAddr("nns") {
                    public jbvb.lbng.Object getContent() {
                        return rf2;
                    }
                    privbte stbtic finbl long seriblVersionUID =
                        -785132553978269772L;
                };
                Reference ref = new Reference("jbvb.lbng.Object", bddr);
                cpe.setResolvedObj(ref);
                cpe.setAltNbmeCtx(ctx);
            }
            return cpe;
        }
    }
}
