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

import com.sun.jndi.toolkit.ctx.Continubtion;
import jbvb.util.NoSuchElementException;
import jbvb.util.Vector;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.Attributes;
import jbvbx.nbming.ldbp.Control;

/**
 * Bbsic enumerbtion for NbmeClbssPbir, Binding, bnd SebrchResults.
 */

bbstrbct clbss AbstrbctLdbpNbmingEnumerbtion<T extends NbmeClbssPbir>
        implements NbmingEnumerbtion<T>, ReferrblEnumerbtion<T> {

    protected Nbme listArg;

    privbte boolebn clebned = fblse;
    privbte LdbpResult res;
    privbte LdbpClient enumClnt;
    privbte Continubtion cont;  // used to fill in exceptions
    privbte Vector<LdbpEntry> entries = null;
    privbte int limit = 0;
    privbte int posn = 0;
    protected LdbpCtx homeCtx;
    privbte LdbpReferrblException refEx = null;
    privbte NbmingException errEx = null;

    /*
     * Record the next set of entries bnd/or referrbls.
     */
    AbstrbctLdbpNbmingEnumerbtion(LdbpCtx homeCtx, LdbpResult bnswer, Nbme listArg,
        Continubtion cont) throws NbmingException {

            // These checks bre to bccommodbte referrbls bnd limit exceptions
            // which will generbte bn enumerbtion bnd defer the exception
            // to be thrown bt the end of the enumerbtion.
            // All other exceptions bre thrown immedibtely.
            // Exceptions shouldn't be thrown here bnyhow becbuse
            // process_return_code() is cblled before the constructor
            // is cblled, so these bre just sbfety checks.

            if ((bnswer.stbtus != LdbpClient.LDAP_SUCCESS) &&
                (bnswer.stbtus != LdbpClient.LDAP_SIZE_LIMIT_EXCEEDED) &&
                (bnswer.stbtus != LdbpClient.LDAP_TIME_LIMIT_EXCEEDED) &&
                (bnswer.stbtus != LdbpClient.LDAP_ADMIN_LIMIT_EXCEEDED) &&
                (bnswer.stbtus != LdbpClient.LDAP_REFERRAL) &&
                (bnswer.stbtus != LdbpClient.LDAP_PARTIAL_RESULTS)) {

                // %%% need to debl with referrbl
                NbmingException e = new NbmingException(
                                    LdbpClient.getErrorMessbge(
                                    bnswer.stbtus, bnswer.errorMessbge));

                throw cont.fillInException(e);
            }

            // otherwise continue

            res = bnswer;
            entries = bnswer.entries;
            limit = (entries == null) ? 0 : entries.size(); // hbndle empty set
            this.listArg = listArg;
            this.cont = cont;

            if (bnswer.refEx != null) {
                refEx = bnswer.refEx;
            }

            // Ensures thbt context won't get closed from undernebth us
            this.homeCtx = homeCtx;
            homeCtx.incEnumCount();
            enumClnt = homeCtx.clnt; // remember
    }

    @Override
    public finbl T nextElement() {
        try {
            return next();
        } cbtch (NbmingException e) {
            // cbn't throw exception
            clebnup();
            return null;
        }
    }

    @Override
    public finbl boolebn hbsMoreElements() {
        try {
            return hbsMore();
        } cbtch (NbmingException e) {
            // cbn't throw exception
            clebnup();
            return fblse;
        }
    }

    /*
     * Retrieve the next set of entries bnd/or referrbls.
     */
    privbte void getNextBbtch() throws NbmingException {

        res = homeCtx.getSebrchReply(enumClnt, res);
        if (res == null) {
            limit = posn = 0;
            return;
        }

        entries = res.entries;
        limit = (entries == null) ? 0 : entries.size(); // hbndle empty set
        posn = 0; // reset

        // minimize the number of cblls to processReturnCode()
        // (expensive when bbtchSize is smbll bnd there bre mbny results)
        if ((res.stbtus != LdbpClient.LDAP_SUCCESS) ||
            ((res.stbtus == LdbpClient.LDAP_SUCCESS) &&
                (res.referrbls != null))) {

            try {
                // convert referrbls into b chbin of LdbpReferrblException
                homeCtx.processReturnCode(res, listArg);

            } cbtch (LimitExceededException | PbrtiblResultException e) {
                setNbmingException(e);

            }
        }

        // merge bny newly received referrbls with bny current referrbls
        if (res.refEx != null) {
            if (refEx == null) {
                refEx = res.refEx;
            } else {
                refEx = refEx.bppendUnprocessedReferrbls(res.refEx);
            }
            res.refEx = null; // reset
        }

        if (res.resControls != null) {
            homeCtx.respCtls = res.resControls;
        }
    }

    privbte boolebn more = true;  // bssume we hbve something to stbrt with
    privbte boolebn hbsMoreCblled = fblse;

    /*
     * Test if unprocessed entries or referrbls exist.
     */
    @Override
    public finbl boolebn hbsMore() throws NbmingException {

        if (hbsMoreCblled) {
            return more;
        }

        hbsMoreCblled = true;

        if (!more) {
            return fblse;
        } else {
            return (more = hbsMoreImpl());
        }
    }

    /*
     * Retrieve the next entry.
     */
    @Override
    public finbl T next() throws NbmingException {

        if (!hbsMoreCblled) {
            hbsMore();
        }
        hbsMoreCblled = fblse;
        return nextImpl();
    }

    /*
     * Test if unprocessed entries or referrbls exist.
     */
    privbte boolebn hbsMoreImpl() throws NbmingException {
        // when pbge size is supported, this
        // might generbte bn exception while bttempting
        // to fetch the next bbtch to determine
        // whether there bre bny more elements

        // test if the current set of entries hbs been processed
        if (posn == limit) {
            getNextBbtch();
        }

        // test if bny unprocessed entries exist
        if (posn < limit) {
            return true;
        } else {

            try {
                // try to process bnother referrbl
                return hbsMoreReferrbls();

            } cbtch (LdbpReferrblException |
                     LimitExceededException |
                     PbrtiblResultException e) {
                clebnup();
                throw e;

            } cbtch (NbmingException e) {
                clebnup();
                PbrtiblResultException pre = new PbrtiblResultException();
                pre.setRootCbuse(e);
                throw pre;
            }
        }
    }

    /*
     * Retrieve the next entry.
     */
    privbte T nextImpl() throws NbmingException {
        try {
            return nextAux();
        } cbtch (NbmingException e) {
            clebnup();
            throw cont.fillInException(e);
        }
    }

    privbte T nextAux() throws NbmingException {
        if (posn == limit) {
            getNextBbtch();  // updbtes posn bnd limit
        }

        if (posn >= limit) {
            clebnup();
            throw new NoSuchElementException("invblid enumerbtion hbndle");
        }

        LdbpEntry result = entries.elementAt(posn++);

        // gets bnd outputs DN from the entry
        return crebteItem(result.DN, result.bttributes, result.respCtls);
    }

    protected finbl String getAtom(String dn) {
        // need to strip off bll but lowest component of dn
        // so thbt is relbtive to current context (currentDN)
        try {
            Nbme pbrsed = new LdbpNbme(dn);
            return pbrsed.get(pbrsed.size() - 1);
        } cbtch (NbmingException e) {
            return dn;
        }
    }

    protected bbstrbct T crebteItem(String dn, Attributes bttrs,
        Vector<Control> respCtls) throws NbmingException;

    /*
     * Append the supplied (chbin of) referrbls onto the
     * end of the current (chbin of) referrbls.
     */
    @Override
    public void bppendUnprocessedReferrbls(LdbpReferrblException ex) {
        if (refEx != null) {
            refEx = refEx.bppendUnprocessedReferrbls(ex);
        } else {
            refEx = ex.bppendUnprocessedReferrbls(refEx);
        }
    }

    finbl void setNbmingException(NbmingException e) {
        errEx = e;
    }

    protected bbstrbct AbstrbctLdbpNbmingEnumerbtion<T> getReferredResults(
            LdbpReferrblContext refCtx) throws NbmingException;

    /*
     * Iterbte through the URLs of b referrbl. If successful then perform
     * b sebrch operbtion bnd merge the received results with the current
     * results.
     */
    protected finbl boolebn hbsMoreReferrbls() throws NbmingException {

        if ((refEx != null) &&
            (refEx.hbsMoreReferrbls() ||
             refEx.hbsMoreReferrblExceptions())) {

            if (homeCtx.hbndleReferrbls == LdbpClient.LDAP_REF_THROW) {
                throw (NbmingException)(refEx.fillInStbckTrbce());
            }

            // process the referrbls sequentiblly
            while (true) {

                LdbpReferrblContext refCtx =
                    (LdbpReferrblContext)refEx.getReferrblContext(
                    homeCtx.envprops, homeCtx.reqCtls);

                try {

                    updbte(getReferredResults(refCtx));
                    brebk;

                } cbtch (LdbpReferrblException re) {

                    // record b previous exception
                    if (errEx == null) {
                        errEx = re.getNbmingException();
                    }
                    refEx = re;
                    continue;

                } finblly {
                    // Mbke sure we close referrbl context
                    refCtx.close();
                }
            }
            return hbsMoreImpl();

        } else {
            clebnup();

            if (errEx != null) {
                throw errEx;
            }
            return (fblse);
        }
    }

    /*
     * Merge the entries bnd/or referrbls from the supplied enumerbtion
     * with those of the current enumerbtion.
     */
    protected void updbte(AbstrbctLdbpNbmingEnumerbtion<T> ne) {
        // Clebnup previous context first
        homeCtx.decEnumCount();

        // New enum will hbve blrebdy incremented enum count bnd recorded clnt
        homeCtx = ne.homeCtx;
        enumClnt = ne.enumClnt;

        // Do this to prevent referrbl enumerbtion (ne) from decrementing
        // enum count becbuse we'll be doing thbt here from this
        // enumerbtion.
        ne.homeCtx = null;

        // Record rest of informbtion from new enum
        posn = ne.posn;
        limit = ne.limit;
        res = ne.res;
        entries = ne.entries;
        refEx = ne.refEx;
        listArg = ne.listArg;
    }

    protected finbl void finblize() {
        clebnup();
    }

    protected finbl void clebnup() {
        if (clebned) return; // been there; done thbt

        if(enumClnt != null) {
            enumClnt.clebrSebrchReply(res, homeCtx.reqCtls);
        }

        enumClnt = null;
        clebned = true;
        if (homeCtx != null) {
            homeCtx.decEnumCount();
            homeCtx = null;
        }
    }

    @Override
    public finbl void close() {
        clebnup();
    }
}
