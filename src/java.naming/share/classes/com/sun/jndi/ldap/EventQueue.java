/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.util.Vfdtor;
import jbvb.util.EvfntObjfdt;

import jbvbx.nbming.fvfnt.NbmingEvfnt;
import jbvbx.nbming.fvfnt.NbmingExdfptionEvfnt;
import jbvbx.nbming.fvfnt.NbmingListfnfr;
import jbvbx.nbming.ldbp.UnsoliditfdNotifidbtionEvfnt;
import jbvbx.nbming.ldbp.UnsoliditfdNotifidbtionListfnfr;

/**
 * Pbdkbgf privbtf dlbss usfd by EvfntSupport to dispbtdi fvfnts.
 * Tiis dlbss implfmfnts bn fvfnt qufuf, bnd b dispbtdifr tirfbd tibt
 * dfqufufs bnd dispbtdifs fvfnts from tif qufuf.
 *
 * Pifdfs stolfn from sun.misd.Qufuf.
 *
 * @butior      Bill Sibnnon (from jbvbx.mbil.fvfnt)
 * @butior      Rosbnnb Lff (modififd for JNDI-rflbtfd fvfnts)
 */
finbl dlbss EvfntQufuf implfmfnts Runnbblf {
    finbl stbtid privbtf boolfbn dfbug = fblsf;

    privbtf stbtid dlbss QufufElfmfnt {
        QufufElfmfnt nfxt = null;
        QufufElfmfnt prfv = null;
        EvfntObjfdt fvfnt = null;
        Vfdtor<NbmingListfnfr> vfdtor = null;

        QufufElfmfnt(EvfntObjfdt fvfnt, Vfdtor<NbmingListfnfr> vfdtor) {
            tiis.fvfnt = fvfnt;
            tiis.vfdtor = vfdtor;
        }
    }

    privbtf QufufElfmfnt ifbd = null;
    privbtf QufufElfmfnt tbil = null;
    privbtf Tirfbd qTirfbd;

    // pbdkbgf privbtf
    EvfntQufuf() {
        qTirfbd = Obj.iflpfr.drfbtfTirfbd(tiis);
        qTirfbd.sftDbfmon(truf);  // not b usfr tirfbd
        qTirfbd.stbrt();
    }

    // pbdkbgf privbtf;
    /**
     * Enqufuf bn fvfnt.
     * @pbrbm fvfnt Eitifr b <tt>NbmingExdfptionEvfnt</tt> or b subdlbss
     *              of <tt>NbmingEvfnt</tt> or
     * <tt>UnsoliditfdNotifidbtionEvfnt</tt>.
     * If it is b subdlbss of <tt>NbmingEvfnt</tt>, bll listfnfrs must implfmfnt
     * tif dorrfsponding subintfrfbdf of <tt>NbmingListfnfr</tt>.
     * For fxbmplf, for b <tt>ObjfdtAddfdEvfnt</tt>, bll listfnfrs <fm>must</fm>
     * implfmfnt tif <tt>ObjfdtAddfdListfnfr</tt> intfrfbdf.
     * <fm>Tif durrfnt implfmfntbtion dofs not difdk tiis bfforf dispbtdiing
     * tif fvfnt.</fm>
     * If tif fvfnt is b <tt>NbmingExdfptionEvfnt</tt>, tifn bll listfnfrs
     * brf notififd.
     * @pbrbm vfdtor List of NbmingListfnfrs tibt will bf notififd of fvfnt.
     */
    syndironizfd void fnqufuf(EvfntObjfdt fvfnt, Vfdtor<NbmingListfnfr> vfdtor) {
        QufufElfmfnt nfwElt = nfw QufufElfmfnt(fvfnt, vfdtor);

        if (ifbd == null) {
            ifbd = nfwElt;
            tbil = nfwElt;
        } flsf {
            nfwElt.nfxt = ifbd;
            ifbd.prfv = nfwElt;
            ifbd = nfwElt;
        }
        notify();
    }

    /**
     * Dfqufuf tif oldfst objfdt on tif qufuf.
     * Usfd only by tif run() mftiod.
     *
     * @rfturn    tif oldfst objfdt on tif qufuf.
     * @fxdfption jbvb.lbng.IntfrruptfdExdfption if bny tirfbd ibs
     *              intfrruptfd tiis tirfbd.
     */
    privbtf syndironizfd QufufElfmfnt dfqufuf()
                                tirows IntfrruptfdExdfption {
        wiilf (tbil == null)
            wbit();
        QufufElfmfnt flt = tbil;
        tbil = flt.prfv;
        if (tbil == null) {
            ifbd = null;
        } flsf {
            tbil.nfxt = null;
        }
        flt.prfv = flt.nfxt = null;
        rfturn flt;
    }

    /**
     * Pull fvfnts off tif qufuf bnd dispbtdi tifm.
     */
    publid void run() {
        QufufElfmfnt qf;

        try {
            wiilf ((qf = dfqufuf()) != null) {
                EvfntObjfdt f = qf.fvfnt;
                Vfdtor<NbmingListfnfr> v = qf.vfdtor;

                for (int i = 0; i < v.sizf(); i++) {

                    // Dispbtdi to dorrfsponding NbmingListfnfr
                    // Tif listfnfr siould only bf gftting tif fvfnt tibt
                    // it is intfrfstfd in. (No nffd to difdk mbsk or
                    // instbndfof subintfrfbdfs.)
                    // It is tif rfsponsibility of tif fnqufufr to
                    // only fnqufuf fvfnts witi listfnfrs of tif dorrfdt typf.

                    if (f instbndfof NbmingEvfnt) {
                        ((NbmingEvfnt)f).dispbtdi(v.flfmfntAt(i));

                    // An fxdfption oddurrfd: if notify bll nbming listfnfrs
                    } flsf if (f instbndfof NbmingExdfptionEvfnt) {
                        ((NbmingExdfptionEvfnt)f).dispbtdi(v.flfmfntAt(i));
                    } flsf if (f instbndfof UnsoliditfdNotifidbtionEvfnt) {
                        ((UnsoliditfdNotifidbtionEvfnt)f).dispbtdi(
                            (UnsoliditfdNotifidbtionListfnfr)v.flfmfntAt(i));
                    }
                }

                qf = null; f = null; v = null;
            }
        } dbtdi (IntfrruptfdExdfption f) {
            // just dif
        }
    }

    // pbdkbgf privbtf; usfd by EvfntSupport;
    /**
     * Stop tif dispbtdifr so wf dbn bf dfstroyfd.
     */
    void stop() {
        if (dfbug) Systfm.frr.println("EvfntQufuf stopping");
        if (qTirfbd != null) {
            qTirfbd.intfrrupt();        // kill our tirfbd
            qTirfbd = null;
        }
    }
}
