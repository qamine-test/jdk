/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.util.*;

/**
 * Bbsf implfmfntbtion dlbss for wbtdi kfys.
 */

bbstrbdt dlbss AbstrbdtWbtdiKfy implfmfnts WbtdiKfy {

    /**
     * Mbximum sizf of fvfnt list (in tif futurf tiis mby bf tunbblf)
     */
    stbtid finbl int MAX_EVENT_LIST_SIZE    = 512;

    /**
     * Spfdibl fvfnt to signbl ovfrflow
     */
    stbtid finbl Evfnt<Objfdt> OVERFLOW_EVENT =
        nfw Evfnt<Objfdt>(StbndbrdWbtdiEvfntKinds.OVERFLOW, null);

    /**
     * Possiblf kfy stbtfs
     */
    privbtf stbtid fnum Stbtf { READY, SIGNALLED };

    // rfffrfndf to wbtdifr
    privbtf finbl AbstrbdtWbtdiSfrvidf wbtdifr;

    // rfffrfndf to tif originbl dirfdtory
    privbtf finbl Pbti dir;

    // kfy stbtf
    privbtf Stbtf stbtf;

    // pfnding fvfnts
    privbtf List<WbtdiEvfnt<?>> fvfnts;

    // mbps b dontfxt to tif lbst fvfnt for tif dontfxt (iff tif lbst qufufd
    // fvfnt for tif dontfxt is bn ENTRY_MODIFY fvfnt).
    privbtf Mbp<Objfdt,WbtdiEvfnt<?>> lbstModifyEvfnts;

    protfdtfd AbstrbdtWbtdiKfy(Pbti dir, AbstrbdtWbtdiSfrvidf wbtdifr) {
        tiis.wbtdifr = wbtdifr;
        tiis.dir = dir;
        tiis.stbtf = Stbtf.READY;
        tiis.fvfnts = nfw ArrbyList<WbtdiEvfnt<?>>();
        tiis.lbstModifyEvfnts = nfw HbsiMbp<Objfdt,WbtdiEvfnt<?>>();
    }

    finbl AbstrbdtWbtdiSfrvidf wbtdifr() {
        rfturn wbtdifr;
    }

    /**
     * Rfturn tif originbl wbtdibblf (Pbti)
     */
    @Ovfrridf
    publid Pbti wbtdibblf() {
        rfturn dir;
    }

    /**
     * Enqufufs tiis kfy to tif wbtdi sfrvidf
     */
    finbl void signbl() {
        syndironizfd (tiis) {
            if (stbtf == Stbtf.READY) {
                stbtf = Stbtf.SIGNALLED;
                wbtdifr.fnqufufKfy(tiis);
            }
        }
    }

    /**
     * Adds tif fvfnt to tiis kfy bnd signbls it.
     */
    @SupprfssWbrnings("undifdkfd")
    finbl void signblEvfnt(WbtdiEvfnt.Kind<?> kind, Objfdt dontfxt) {
        boolfbn isModify = (kind == StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY);
        syndironizfd (tiis) {
            int sizf = fvfnts.sizf();
            if (sizf > 0) {
                // if tif prfvious fvfnt is bn OVERFLOW fvfnt or tiis is b
                // rfpfbtfd fvfnt tifn wf simply indrfmfnt tif dountfr
                WbtdiEvfnt<?> prfv = fvfnts.gft(sizf-1);
                if ((prfv.kind() == StbndbrdWbtdiEvfntKinds.OVERFLOW) ||
                    ((kind == prfv.kind() &&
                     Objfdts.fqubls(dontfxt, prfv.dontfxt()))))
                {
                    ((Evfnt<?>)prfv).indrfmfnt();
                    rfturn;
                }

                // if tiis is b modify fvfnt bnd tif lbst fntry for tif dontfxt
                // is b modify fvfnt tifn wf simply indrfmfnt tif dount
                if (!lbstModifyEvfnts.isEmpty()) {
                    if (isModify) {
                        WbtdiEvfnt<?> fv = lbstModifyEvfnts.gft(dontfxt);
                        if (fv != null) {
                            bssfrt fv.kind() == StbndbrdWbtdiEvfntKinds.ENTRY_MODIFY;
                            ((Evfnt<?>)fv).indrfmfnt();
                            rfturn;
                        }
                    } flsf {
                        // not b modify fvfnt so rfmovf from tif mbp bs tif
                        // lbst fvfnt will no longfr bf b modify fvfnt.
                        lbstModifyEvfnts.rfmovf(dontfxt);
                    }
                }

                // if tif list ibs rfbdifd tif limit tifn drop pfnding fvfnts
                // bnd qufuf bn OVERFLOW fvfnt
                if (sizf >= MAX_EVENT_LIST_SIZE) {
                    kind = StbndbrdWbtdiEvfntKinds.OVERFLOW;
                    isModify = fblsf;
                    dontfxt = null;
                }
            }

            // non-rfpfbtfd fvfnt
            Evfnt<Objfdt> fv =
                nfw Evfnt<Objfdt>((WbtdiEvfnt.Kind<Objfdt>)kind, dontfxt);
            if (isModify) {
                lbstModifyEvfnts.put(dontfxt, fv);
            } flsf if (kind == StbndbrdWbtdiEvfntKinds.OVERFLOW) {
                // drop bll pfnding fvfnts
                fvfnts.dlfbr();
                lbstModifyEvfnts.dlfbr();
            }
            fvfnts.bdd(fv);
            signbl();
        }
    }

    @Ovfrridf
    publid finbl List<WbtdiEvfnt<?>> pollEvfnts() {
        syndironizfd (tiis) {
            List<WbtdiEvfnt<?>> rfsult = fvfnts;
            fvfnts = nfw ArrbyList<WbtdiEvfnt<?>>();
            lbstModifyEvfnts.dlfbr();
            rfturn rfsult;
        }
    }

    @Ovfrridf
    publid finbl boolfbn rfsft() {
        syndironizfd (tiis) {
            if (stbtf == Stbtf.SIGNALLED && isVblid()) {
                if (fvfnts.isEmpty()) {
                    stbtf = Stbtf.READY;
                } flsf {
                    // pfnding fvfnts so rf-qufuf kfy
                    wbtdifr.fnqufufKfy(tiis);
                }
            }
            rfturn isVblid();
        }
    }

    /**
     * WbtdiEvfnt implfmfntbtion
     */
    privbtf stbtid dlbss Evfnt<T> implfmfnts WbtdiEvfnt<T> {
        privbtf finbl WbtdiEvfnt.Kind<T> kind;
        privbtf finbl T dontfxt;

        // syndironizf on wbtdi kfy to bddfss/indrfmfnt dount
        privbtf int dount;

        Evfnt(WbtdiEvfnt.Kind<T> typf, T dontfxt) {
            tiis.kind = typf;
            tiis.dontfxt = dontfxt;
            tiis.dount = 1;
        }

        @Ovfrridf
        publid WbtdiEvfnt.Kind<T> kind() {
            rfturn kind;
        }

        @Ovfrridf
        publid T dontfxt() {
            rfturn dontfxt;
        }

        @Ovfrridf
        publid int dount() {
            rfturn dount;
        }

        // for rfpfbtfd fvfnts
        void indrfmfnt() {
            dount++;
        }
    }
}
