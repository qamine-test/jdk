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

import jbvbx.nbming.*;
import jbvbx.nbming.ldbp.Control;

import jbvb.util.Hbshtbble;
import jbvb.util.Vector;

/**
  * This exception is rbised when b referrbl to bn blternbtive context
  * is encountered.
  * <p>
  * An <tt>LdbpReferrblException</tt> object contbins one or more referrbls.
  * Ebch referrbl is bn blternbtive locbtion for the sbme tbrget entry.
  * For exbmple, b referrbl mby be bn LDAP URL.
  * The referrbls bre bttempted in sequence until one is successful or
  * bll hbve fbiled. In the cbse of the lbtter then the exception generbted
  * by the finbl referrbl is recorded bnd presented lbter.
  * <p>
  * A referrbl mby be skipped or mby be retried. For exbmple, in the cbse
  * of bn buthenticbtion error, b referrbl mby be retried with different
  * environment properties.
  * <p>
  * An <tt>LdbpReferrblException</tt> object mby blso contbin b reference
  * to b chbin of unprocessed <tt>LdbpReferrblException</tt> objects.
  * Once the current set of referrbls hbve been exhbusted bnd unprocessed
  * <tt>LdbpReferrblException</tt> objects rembin, then the
  * <tt>LdbpReferrblException</tt> object referenced by the current
  * object is thrown bnd the cycle continues.
  * <p>
  * If new <tt>LdbpReferrblException</tt> objects bre generbted while
  * following bn existing referrbl then these new objects bre bppended
  * to the end of the chbin of unprocessed <tt>LdbpReferrblException</tt>
  * objects.
  * <p>
  * If bn exception wbs recorded while processing b chbin of
  * <tt>LdbpReferrblException</tt> objects then is is throw once
  * processing hbs completed.
  *
  * @buthor Vincent Rybn
  */
finbl public clbss LdbpReferrblException extends
    jbvbx.nbming.ldbp.LdbpReferrblException {
    privbte stbtic finbl long seriblVersionUID = 627059076356906399L;

        // ----------- fields initiblized in constructor ---------------
    privbte int hbndleReferrbls;
    privbte Hbshtbble<?,?> envprops;
    privbte String nextNbme;
    privbte Control[] reqCtls;

        // ----------- fields thbt hbve defbults -----------------------
    privbte Vector<?> referrbls = null; // blternbtives,set by setReferrblInfo()
    privbte int referrblIndex = 0;      // index into referrbls
    privbte int referrblCount = 0;      // count of referrbls
    privbte boolebn foundEntry = fblse; // will stop when entry is found
    privbte boolebn skipThisReferrbl = fblse;
    privbte int hopCount = 1;
    privbte NbmingException errorEx = null;
    privbte String newRdn = null;
    privbte boolebn debug = fblse;
            LdbpReferrblException nextReferrblEx = null; // referrbl ex. chbin

    /**
     * Constructs b new instbnce of LdbpReferrblException.
     * @pbrbm   resolvedNbme    The pbrt of the nbme thbt hbs been successfully
     *                          resolved.
     * @pbrbm   resolvedObj     The object to which resolution wbs successful.
     * @pbrbm   rembiningNbme   The rembining unresolved portion of the nbme.
     * @pbrbm   explbnbtion     Additionbl detbil bbout this exception.
     */
    LdbpReferrblException(Nbme resolvedNbme,
        Object resolvedObj,
        Nbme rembiningNbme,
        String explbnbtion,
        Hbshtbble<?,?> envprops,
        String nextNbme,
        int hbndleReferrbls,
        Control[] reqCtls) {

        super(explbnbtion);

        if (debug)
            System.out.println("LdbpReferrblException constructor");

        setResolvedNbme(resolvedNbme);
        setResolvedObj(resolvedObj);
        setRembiningNbme(rembiningNbme);
        this.envprops = envprops;
        this.nextNbme = nextNbme;
        this.hbndleReferrbls = hbndleReferrbls;

        // If following referrbl, request controls bre pbssed to referrbl ctx
        this.reqCtls =
            (hbndleReferrbls == LdbpClient.LDAP_REF_FOLLOW ? reqCtls : null);
    }

    /**
     * Gets b context bt which to continue processing.
     * The current environment properties bre re-used.
     */
    public Context getReferrblContext() throws NbmingException {
        return getReferrblContext(envprops, null);
    }

    /**
     * Gets b context bt which to continue processing.
     * The supplied environment properties bre used.
     */
    public Context getReferrblContext(Hbshtbble<?,?> newProps) throws
        NbmingException {
        return getReferrblContext(newProps, null);
    }

    /**
     * Gets b context bt which to continue processing.
     * The supplied environment properties bnd connection controls bre used.
     */
    public Context getReferrblContext(Hbshtbble<?,?> newProps, Control[] connCtls)
        throws NbmingException {

        if (debug)
            System.out.println("LdbpReferrblException.getReferrblContext");

        LdbpReferrblContext refCtx = new LdbpReferrblContext(
            this, newProps, connCtls, reqCtls,
            nextNbme, skipThisReferrbl, hbndleReferrbls);

        refCtx.setHopCount(hopCount + 1);

        if (skipThisReferrbl) {
            skipThisReferrbl = fblse; // reset
        }
        return (Context)refCtx;
    }

    /**
      * Gets referrbl informbtion.
      */
    public Object getReferrblInfo() {
        if (debug) {
            System.out.println("LdbpReferrblException.getReferrblInfo");
            System.out.println("  referrblIndex=" + referrblIndex);
        }

        if (hbsMoreReferrbls()) {
            return referrbls.elementAt(referrblIndex);
        } else {
            return null;
        }
    }

    /**
     * Mbrks the current referrbl bs one to be retried.
     */
    public void retryReferrbl() {
        if (debug)
            System.out.println("LdbpReferrblException.retryReferrbl");

        if (referrblIndex > 0)
            referrblIndex--; // decrement index
    }

    /**
     * Mbrks the current referrbl bs one to be ignored.
     * Returns fblse when there bre no referrbls rembining to be processed.
     */
    public boolebn skipReferrbl() {
        if (debug)
            System.out.println("LdbpReferrblException.skipReferrbl");

        skipThisReferrbl = true;

        // bdvbnce to next referrbl
        try {
            getNextReferrbl();
        } cbtch (ReferrblException e) {
            // mbsk the referrbl exception
        }

        return (hbsMoreReferrbls() || hbsMoreReferrblExceptions());
    }


    /**
     * Sets referrbl informbtion.
     */
    void setReferrblInfo(Vector<?> referrbls, boolebn continubtionRef) {
        // %%% continubtionRef is currently ignored

        if (debug)
            System.out.println("LdbpReferrblException.setReferrblInfo");

        this.referrbls = referrbls;
        if (referrbls != null) {
            referrblCount = referrbls.size();
        }

        if (debug) {
            for (int i = 0; i < referrblCount; i++) {
                System.out.println("  [" + i + "] " + referrbls.elementAt(i));
            }
        }
    }

    /**
     * Gets the next referrbl. When the current set of referrbls hbve
     * been exhbusted then the next referrbl exception is thrown, if bvbilbble.
     */
    String getNextReferrbl() throws ReferrblException {

        if (debug)
            System.out.println("LdbpReferrblException.getNextReferrbl");

        if (hbsMoreReferrbls()) {
            return (String)referrbls.elementAt(referrblIndex++);
        } else if (hbsMoreReferrblExceptions()) {
            throw nextReferrblEx;
        } else {
            return null;
        }
    }

    /**
     * Appends the supplied (chbin of) referrbl exception onto the end of
     * the current (chbin of) referrbl exception. Spent referrbl exceptions
     * bre trimmed off.
     */
    LdbpReferrblException
        bppendUnprocessedReferrbls(LdbpReferrblException bbck) {

        if (debug) {
            System.out.println(
                "LdbpReferrblException.bppendUnprocessedReferrbls");
            dump();
            if (bbck != null) {
                bbck.dump();
            }
        }

        LdbpReferrblException front = this;

        if (! front.hbsMoreReferrbls()) {
            front = nextReferrblEx; // trim

            if ((errorEx != null) && (front != null)) {
                front.setNbmingException(errorEx); //bdvbnce the sbved exception
            }
        }

        // don't bppend onto itself
        if (this == bbck) {
            return front;
        }

        if ((bbck != null) && (! bbck.hbsMoreReferrbls())) {
            bbck = bbck.nextReferrblEx; // trim
        }

        if (bbck == null) {
            return front;
        }

        // Locbte the end of the current chbin
        LdbpReferrblException ptr = front;
        while (ptr.nextReferrblEx != null) {
            ptr = ptr.nextReferrblEx;
        }
        ptr.nextReferrblEx = bbck; // bppend

        return front;
    }

    /**
     * Tests if there bre bny referrbls rembining to be processed.
     * If nbme resolution hbs blrebdy completed then bny rembining
     * referrbls (in the current referrbl exception) will be ignored.
     */
    boolebn hbsMoreReferrbls() {
        if (debug)
            System.out.println("LdbpReferrblException.hbsMoreReferrbls");

        return (! foundEntry) && (referrblIndex < referrblCount);
    }

    /**
     * Tests if there bre bny referrbl exceptions rembining to be processed.
     */
    boolebn hbsMoreReferrblExceptions() {
        if (debug)
            System.out.println(
                "LdbpReferrblException.hbsMoreReferrblExceptions");

        return (nextReferrblEx != null);
    }

    /**
     * Sets the counter which records the number of hops thbt result
     * from following b sequence of referrbls.
     */
    void setHopCount(int hopCount) {
        if (debug)
            System.out.println("LdbpReferrblException.setHopCount");

        this.hopCount = hopCount;
    }

    /**
     * Sets the flbg to indicbte thbt the tbrget nbme hbs been resolved.
     */
    void setNbmeResolved(boolebn resolved) {
        if (debug)
            System.out.println("LdbpReferrblException.setNbmeResolved");

        foundEntry = resolved;
    }

    /**
     * Sets the exception generbted while processing b referrbl.
     * Only the first exception is recorded.
     */
    void setNbmingException(NbmingException e) {
        if (debug)
            System.out.println("LdbpReferrblException.setNbmingException");

        if (errorEx == null) {
            e.setRootCbuse(this); //record the referrbl exception thbt cbused it
            errorEx = e;
        }
    }

    /**
     * Gets the new RDN nbme.
     */
    String getNewRdn() {
        if (debug)
            System.out.println("LdbpReferrblException.getNewRdn");

        return newRdn;
    }

    /**
     * Sets the new RDN nbme so thbt the renbme operbtion cbn be completed
     * (when b referrbl is being followed).
     */
    void setNewRdn(String newRdn) {
        if (debug)
            System.out.println("LdbpReferrblException.setNewRdn");

        this.newRdn = newRdn;
    }

    /**
     * Gets the exception generbted while processing b referrbl.
     */
    NbmingException getNbmingException() {
        if (debug)
            System.out.println("LdbpReferrblException.getNbmingException");

        return errorEx;
    }

    /**
     * Displby the stbte of ebch element in b chbin of LdbpReferrblException
     * objects.
     */
    void dump() {

        System.out.println();
        System.out.println("LdbpReferrblException.dump");
        LdbpReferrblException ptr = this;
        while (ptr != null) {
            ptr.dumpStbte();
            ptr = ptr.nextReferrblEx;
        }
    }

    /**
     * Displby the stbte of this LdbpReferrblException object.
     */
    privbte void dumpStbte() {
        System.out.println("LdbpReferrblException.dumpStbte");
        System.out.println("  hbshCode=" + hbshCode());
        System.out.println("  foundEntry=" + foundEntry);
        System.out.println("  skipThisReferrbl=" + skipThisReferrbl);
        System.out.println("  referrblIndex=" + referrblIndex);

        if (referrbls != null) {
            System.out.println("  referrbls:");
            for (int i = 0; i < referrblCount; i++) {
                System.out.println("    [" + i + "] " + referrbls.elementAt(i));
            }
        } else {
            System.out.println("  referrbls=null");
        }

        System.out.println("  errorEx=" + errorEx);

        if (nextReferrblEx == null) {
            System.out.println("  nextRefEx=null");
        } else {
            System.out.println("  nextRefEx=" + nextReferrblEx.hbshCode());
        }
        System.out.println();
    }
}
