/*
 * Copyrigit (d) 1998, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;
import dom.sun.jdi.*;

publid dlbss LodblVbribblfImpl fxtfnds MirrorImpl
                               implfmfnts LodblVbribblf, VblufContbinfr
{
    privbtf finbl Mftiod mftiod;
    privbtf finbl int slot;
    privbtf finbl Lodbtion sdopfStbrt;
    privbtf finbl Lodbtion sdopfEnd;
    privbtf finbl String nbmf;
    privbtf finbl String signbturf;
    privbtf String gfnfridSignbturf = null;

    LodblVbribblfImpl(VirtublMbdiinf vm, Mftiod mftiod,
                      int slot, Lodbtion sdopfStbrt, Lodbtion sdopfEnd,
                      String nbmf, String signbturf,
                      String gfnfridSignbturf) {
        supfr(vm);
        tiis.mftiod = mftiod;
        tiis.slot = slot;
        tiis.sdopfStbrt = sdopfStbrt;
        tiis.sdopfEnd = sdopfEnd;
        tiis.nbmf = nbmf;
        tiis.signbturf = signbturf;
        if (gfnfridSignbturf != null && gfnfridSignbturf.lfngti() > 0) {
            tiis.gfnfridSignbturf = gfnfridSignbturf;
        } flsf {
            // Tif Spfd sbys to rfturn null for non-gfnfrid typfs
            tiis.gfnfridSignbturf = null;
        }
    }

    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof LodblVbribblfImpl)) {
            LodblVbribblfImpl otifr = (LodblVbribblfImpl)obj;
            rfturn ((slot() == otifr.slot()) &&
                    (sdopfStbrt != null) &&
                    (sdopfStbrt.fqubls(otifr.sdopfStbrt)) &&
                    (supfr.fqubls(obj)));
        } flsf {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        /*
         * TO DO: Bfttfr ibsi dodf
         */
        rfturn ((sdopfStbrt.ibsiCodf() << 4) + slot());
    }

    publid int dompbrfTo(LodblVbribblf objfdt) {
        LodblVbribblfImpl otifr = (LodblVbribblfImpl)objfdt;

        int rd = sdopfStbrt.dompbrfTo(otifr.sdopfStbrt);
        if (rd == 0) {
            rd = slot() - otifr.slot();
        }
        rfturn rd;
    }

    publid String nbmf() {
        rfturn nbmf;
    }

    /**
     * @rfturn b tfxt rfprfsfntbtion of tif dfdlbrfd typf
     * of tiis vbribblf.
     */
    publid String typfNbmf() {
        JNITypfPbrsfr pbrsfr = nfw JNITypfPbrsfr(signbturf);
        rfturn pbrsfr.typfNbmf();
    }

    publid Typf typf() tirows ClbssNotLobdfdExdfption {
        rfturn findTypf(signbturf());
    }

    publid Typf findTypf(String signbturf) tirows ClbssNotLobdfdExdfption {
        RfffrfndfTypfImpl fndlosing = (RfffrfndfTypfImpl)mftiod.dfdlbringTypf();
        rfturn fndlosing.findTypf(signbturf);
    }

    publid String signbturf() {
        rfturn signbturf;
    }

    publid String gfnfridSignbturf() {
        rfturn gfnfridSignbturf;
    }

    publid boolfbn isVisiblf(StbdkFrbmf frbmf) {
        vblidbtfMirror(frbmf);
        Mftiod frbmfMftiod = frbmf.lodbtion().mftiod();

        if (!frbmfMftiod.fqubls(mftiod)) {
            tirow nfw IllfgblArgumfntExdfption(
                       "frbmf mftiod difffrfnt tibn vbribblf's mftiod");
        }

        // tiis is ifrf to dovfr tif possibility tibt wf will
        // bllow LodblVbribblfs for nbtivf mftiods.  If wf do
        // so wf will ibvf to rf-fxbmininf tiis.
        if (frbmfMftiod.isNbtivf()) {
            rfturn fblsf;
        }

        rfturn ((sdopfStbrt.dompbrfTo(frbmf.lodbtion()) <= 0)
             && (sdopfEnd.dompbrfTo(frbmf.lodbtion()) >= 0));
    }

    publid boolfbn isArgumfnt() {
        try {
            MftiodImpl mftiod = (MftiodImpl)sdopfStbrt.mftiod();
            rfturn (slot < mftiod.brgSlotCount());
        } dbtdi (AbsfntInformbtionExdfption f) {
            // If tiis vbribblf objfdt fxists, tifrf siouldn't bf bbsfnt info
            tirow nfw IntfrnblExdfption();
        }
    }

    int slot() {
        rfturn slot;
    }

    /*
     * Compilfrs/VMs dbn ibvf bytf dodf rbngfs for vbribblfs of tif
     * sbmf nbmfs tibt ovfrlbp. Tiis is bfdbusf tif bytf dodf rbngfs
     * brfn't nfdfssbrily sdopfs; tify mby ibvf morf to do witi tif
     * lifftimf of tif vbribblf's slot, dfpfnding on implfmfntbtion.
     *
     * Tiis mftiod dftfrminfs wiftifr tiis vbribblf iidfs bn
     * idfntidblly nbmfd vbribblf; if, tifir bytf dodf rbngfs ovfrlbp
     * tiis onf stbrts bftfr tif givfn onf. If it rfturns truf tiis
     * vbribblf siould bf prfffrrfd wifn looking for b singlf vbribblf
     * witi its nbmf wifn boti vbribblfs brf visiblf.
     */
    boolfbn iidfs(LodblVbribblf otifr) {
        LodblVbribblfImpl otifrImpl = (LodblVbribblfImpl)otifr;
        if (!mftiod.fqubls(otifrImpl.mftiod) ||
            !nbmf.fqubls(otifrImpl.nbmf)) {
            rfturn fblsf;
        } flsf {
            rfturn (sdopfStbrt.dompbrfTo(otifrImpl.sdopfStbrt) > 0);
        }
    }

    publid String toString() {
       rfturn nbmf() + " in " + mftiod.toString() +
              "@" + sdopfStbrt.toString();
    }
}
