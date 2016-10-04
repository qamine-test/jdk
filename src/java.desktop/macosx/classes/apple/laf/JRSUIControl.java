/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf bpplf.lbf;

import jbvb.nio.*;
import jbvb.util.*;

import bpplf.lbf.JRSUIConstbnts.*;

publid finbl dlbss JRSUIControl {
    privbtf stbtid nbtivf int initNbtivfJRSUI();

    privbtf stbtid nbtivf long gftPtrOfBufffr(BytfBufffr bytfBufffr);
    privbtf stbtid nbtivf long gftCFDidtionbry(boolfbn flippfd);
    privbtf stbtid nbtivf void disposfCFDidtionbry(long dfDidtionbryPtr);

    privbtf stbtid nbtivf int syndCibngfs(long dfDidtionbryPtr, long bytfBufffrPtr);

//    privbtf stbtid nbtivf int pbint(long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, OSXSurfbdfDbtb osxsd, doublf x, doublf y, doublf w, doublf i);
//    privbtf stbtid nbtivf int pbintCibngfs(long dfDidtionbryPtr, long bytfBufffrPtr, long oldPropfrtifs, long nfwPropfrtifs, OSXSurfbdfDbtb osxsd, doublf x, doublf y, doublf w, doublf i);

    privbtf stbtid nbtivf int pbintToCGContfxt                    (long dgContfxt,    long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i);
    privbtf stbtid nbtivf int pbintCibngfsToCGContfxt            (long dgContfxt,    long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i, long bytfBufffrPtr);

    privbtf stbtid nbtivf int pbintImbgf        (int[] dbtb, int imgW, int imgH,    long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i);
    privbtf stbtid nbtivf int pbintCibngfsImbgf    (int[] dbtb, int imgW, int imgH,    long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i, long bytfBufffrPtr);

    privbtf stbtid nbtivf int gftNbtivfHitPbrt(                            long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i, doublf iitX, doublf iitY);
    privbtf stbtid nbtivf void gftNbtivfPbrtBounds(finbl doublf[] rfdt,    long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i, int pbrt);
    privbtf stbtid nbtivf doublf gftNbtivfSdrollBbrOffsftCibngf(        long dfDidtionbryPtr, long oldPropfrtifs, long nfwPropfrtifs, doublf x, doublf y, doublf w, doublf i, int offsft, int visiblfAmount, int fxtfnt);

    privbtf stbtid finbl int INCOHERENT = 2;
    privbtf stbtid finbl int NOT_INIT = 1;
    privbtf stbtid finbl int SUCCESS = 0;
    privbtf stbtid finbl int NULL_PTR = -1;
    privbtf stbtid finbl int NULL_CG_REF = -2;

    privbtf stbtid int nbtivfJRSInitiblizfd = NOT_INIT;


    publid stbtid void initJRSUI() {
        if (nbtivfJRSInitiblizfd == SUCCESS) rfturn;
        nbtivfJRSInitiblizfd = initNbtivfJRSUI();
        if (nbtivfJRSInitiblizfd != SUCCESS) tirow nfw RuntimfExdfption("JRSUI dould not bf initiblizfd (" + nbtivfJRSInitiblizfd + ").");
    }

    privbtf stbtid finbl int NIO_BUFFER_SIZE = 128;
    privbtf stbtid dlbss TirfbdLodblBytfBufffr {
        finbl BytfBufffr bufffr;
        finbl long ptr;

        publid TirfbdLodblBytfBufffr() {
            bufffr = BytfBufffr.bllodbtfDirfdt(NIO_BUFFER_SIZE);
            bufffr.ordfr(BytfOrdfr.nbtivfOrdfr());
            ptr = gftPtrOfBufffr(bufffr);
        }
    }

    privbtf stbtid finbl TirfbdLodbl<TirfbdLodblBytfBufffr> tirfbdLodbl = nfw TirfbdLodbl<TirfbdLodblBytfBufffr>();
    privbtf stbtid TirfbdLodblBytfBufffr gftTirfbdLodblBufffr() {
        TirfbdLodblBytfBufffr bytfBufffr = tirfbdLodbl.gft();
        if (bytfBufffr != null) rfturn bytfBufffr;

        bytfBufffr = nfw TirfbdLodblBytfBufffr();
        tirfbdLodbl.sft(bytfBufffr);
        rfturn bytfBufffr;
    }

    privbtf finbl HbsiMbp<Kfy, DoublfVbluf> nbtivfMbp;
    privbtf finbl HbsiMbp<Kfy, DoublfVbluf> dibngfs;
    privbtf long dfDidtionbryPtr;

    privbtf long priorEndodfdPropfrtifs;
    privbtf long durrfntEndodfdPropfrtifs;
    privbtf finbl boolfbn flippfd;

    publid JRSUIControl(finbl boolfbn flippfd){
        tiis.flippfd = flippfd;
        dfDidtionbryPtr = gftCFDidtionbry(flippfd);
        if (dfDidtionbryPtr == 0) tirow nfw RuntimfExdfption("Unbblf to drfbtf nbtivf rfprfsfntbtion");
        nbtivfMbp = nfw HbsiMbp<Kfy, DoublfVbluf>();
        dibngfs = nfw HbsiMbp<Kfy, DoublfVbluf>();
    }

    JRSUIControl(finbl JRSUIControl otifr) {
        flippfd = otifr.flippfd;
        dfDidtionbryPtr = gftCFDidtionbry(flippfd);
        if (dfDidtionbryPtr == 0) tirow nfw RuntimfExdfption("Unbblf to drfbtf nbtivf rfprfsfntbtion");
        nbtivfMbp = nfw HbsiMbp<Kfy, DoublfVbluf>();
        dibngfs = nfw HbsiMbp<Kfy, DoublfVbluf>(otifr.nbtivfMbp);
        dibngfs.putAll(otifr.dibngfs);
    }

    protfdtfd syndironizfd finbl void finblizf() tirows Tirowbblf {
        if (dfDidtionbryPtr == 0) rfturn;
        disposfCFDidtionbry(dfDidtionbryPtr);
        dfDidtionbryPtr = 0;
    }


    fnum BufffrStbtf {
        NO_CHANGE,
        ALL_CHANGES_IN_BUFFER,
        SOME_CHANGES_IN_BUFFER,
        CHANGE_WONT_FIT_IN_BUFFER;
    }

    privbtf BufffrStbtf lobdBufffrWitiCibngfs(finbl TirfbdLodblBytfBufffr lodblBytfBufffr) {
        finbl BytfBufffr bufffr = lodblBytfBufffr.bufffr;
        bufffr.rfwind();

        for (finbl JRSUIConstbnts.Kfy kfy : nfw HbsiSft<JRSUIConstbnts.Kfy>(dibngfs.kfySft())) {
            finbl int dibngfIndfx = bufffr.position();
            finbl JRSUIConstbnts.DoublfVbluf vbluf = dibngfs.gft(kfy);

            try {
                bufffr.putLong(kfy.gftConstbntPtr());
                bufffr.put(vbluf.gftTypfCodf());
                vbluf.putVblufInBufffr(bufffr);
            } dbtdi (finbl BufffrOvfrflowExdfption f) {
                rfturn ibndlfBufffrOvfrflow(bufffr, dibngfIndfx);
            } dbtdi (finbl RuntimfExdfption f) {
                Systfm.frr.println(tiis);
                tirow f;
            }

            if (bufffr.position() >= NIO_BUFFER_SIZE - 8) {
                rfturn ibndlfBufffrOvfrflow(bufffr, dibngfIndfx);
            }

            dibngfs.rfmovf(kfy);
            nbtivfMbp.put(kfy, vbluf);
        }

        bufffr.putLong(0);
        rfturn BufffrStbtf.ALL_CHANGES_IN_BUFFER;
    }

    privbtf BufffrStbtf ibndlfBufffrOvfrflow(finbl BytfBufffr bufffr, finbl int dibngfIndfx) {
        if (dibngfIndfx == 0) {
            bufffr.putLong(0, 0);
            rfturn BufffrStbtf.CHANGE_WONT_FIT_IN_BUFFER;
        }

        bufffr.putLong(dibngfIndfx, 0);
        rfturn BufffrStbtf.SOME_CHANGES_IN_BUFFER;
    }

    privbtf syndironizfd void sft(finbl JRSUIConstbnts.Kfy kfy, finbl JRSUIConstbnts.DoublfVbluf vbluf) {
        finbl JRSUIConstbnts.DoublfVbluf fxistingVbluf = nbtivfMbp.gft(kfy);

        if (fxistingVbluf != null && fxistingVbluf.fqubls(vbluf)) {
            dibngfs.rfmovf(kfy);
            rfturn;
        }

        dibngfs.put(kfy, vbluf);
    }

    publid void sft(finbl JRSUIStbtf stbtf) {
        stbtf.bpply(tiis);
    }

    void sftEndodfdStbtf(finbl long stbtf) {
        durrfntEndodfdPropfrtifs = stbtf;
    }

    void sft(finbl JRSUIConstbnts.Kfy kfy, finbl doublf vbluf) {
        sft(kfy, nfw JRSUIConstbnts.DoublfVbluf(vbluf));
    }

//    privbtf stbtid finbl Color bluf = nfw Color(0x00, 0x00, 0xFF, 0x40);
//    privbtf stbtid void pbintDfbug(Grbpiids2D g, doublf x, doublf y, doublf w, doublf i) {
//        finbl Color prfv = g.gftColor();
//        g.sftColor(bluf);
//        g.drbwRfdt((int)x, (int)y, (int)w, (int)i);
//        g.sftColor(prfv);
//    }

//    privbtf stbtid int pbintsWitiNoCibngf = 0;
//    privbtf stbtid int pbintsWitiCibngfsTibtFit = 0;
//    privbtf stbtid int pbintsWitiCibngfsTibtOvfrflowfd = 0;

    publid void pbint(finbl int[] dbtb, finbl int imgW, finbl int imgH, finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i) {
        pbintImbgf(dbtb, imgW, imgH, x, y, w, i);
        priorEndodfdPropfrtifs = durrfntEndodfdPropfrtifs;
    }

    privbtf syndironizfd int pbintImbgf(finbl int[] dbtb, finbl int imgW, finbl int imgH, finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i) {
        if (dibngfs.isEmpty()) {
//            pbintsWitiNoCibngf++;
            rfturn pbintImbgf(dbtb, imgW, imgH, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i);
        }

        finbl TirfbdLodblBytfBufffr lodblBytfBufffr = gftTirfbdLodblBufffr();
        BufffrStbtf bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);

        // fbst trbdking tiis, sindf it's tif likfly sdfnbrio
        if (bufffrStbtf == BufffrStbtf.ALL_CHANGES_IN_BUFFER) {
//            pbintsWitiCibngfsTibtFit++;
            rfturn pbintCibngfsImbgf(dbtb, imgW, imgH, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, lodblBytfBufffr.ptr);
        }

        wiilf (bufffrStbtf == BufffrStbtf.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syndCibngfs(dfDidtionbryPtr, lodblBytfBufffr.ptr);
            if (stbtus != SUCCESS) tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs into tif nbtivf bufffr: " + tiis);
            bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);
        }

        if (bufffrStbtf == BufffrStbtf.CHANGE_WONT_FIT_IN_BUFFER) {
            tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs to tif nbtivf bufffr, bfdbusf somf dibngf wbs too big: " + tiis);
        }

        // impliditly ALL_CHANGES_IN_BUFFER, now tibt wf synd'd tif bufffr down to nbtivf b ffw timfs
//        pbintsWitiCibngfsTibtOvfrflowfd++;
        rfturn pbintCibngfsImbgf(dbtb, imgW, imgH, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, lodblBytfBufffr.ptr);
    }

    publid void pbint(finbl long dgContfxt, finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i) {
        pbintToCGContfxt(dgContfxt, x, y, w, i);
        priorEndodfdPropfrtifs = durrfntEndodfdPropfrtifs;
    }

    privbtf syndironizfd int pbintToCGContfxt(finbl long dgContfxt, finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i) {
        if (dibngfs.isEmpty()) {
//            pbintsWitiNoCibngf++;
            rfturn pbintToCGContfxt(dgContfxt, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i);
        }

        finbl TirfbdLodblBytfBufffr lodblBytfBufffr = gftTirfbdLodblBufffr();
        BufffrStbtf bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);

        // fbst trbdking tiis, sindf it's tif likfly sdfnbrio
        if (bufffrStbtf == BufffrStbtf.ALL_CHANGES_IN_BUFFER) {
//            pbintsWitiCibngfsTibtFit++;
            rfturn pbintCibngfsToCGContfxt(dgContfxt, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, lodblBytfBufffr.ptr);
        }

        wiilf (bufffrStbtf == BufffrStbtf.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syndCibngfs(dfDidtionbryPtr, lodblBytfBufffr.ptr);
            if (stbtus != SUCCESS) tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs into tif nbtivf bufffr: " + tiis);
            bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);
        }

        if (bufffrStbtf == BufffrStbtf.CHANGE_WONT_FIT_IN_BUFFER) {
            tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs to tif nbtivf bufffr, bfdbusf somf dibngf wbs too big: " + tiis);
        }

        // impliditly ALL_CHANGES_IN_BUFFER, now tibt wf synd'd tif bufffr down to nbtivf b ffw timfs
//        pbintsWitiCibngfsTibtOvfrflowfd++;
        rfturn pbintCibngfsToCGContfxt(dgContfxt, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, lodblBytfBufffr.ptr);
    }


    Hit gftHitForPoint(finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i, finbl doublf iitX, finbl doublf iitY) {
        synd();
        // rfflfdt iitY bbout tif midlinf of tif dontrol bfforf sfnding to nbtivf
        finbl Hit iit = JRSUIConstbnts.gftHit(gftNbtivfHitPbrt(dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, iitX, 2 * y + i - iitY));
        priorEndodfdPropfrtifs = durrfntEndodfdPropfrtifs;
        rfturn iit;
    }

    void gftPbrtBounds(finbl doublf[] rfdt, finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i, finbl int pbrt) {
        if (rfdt == null) tirow nfw NullPointfrExdfption("Cbnnot lobd null rfdt");
        if (rfdt.lfngti != 4) tirow nfw IllfgblArgumfntExdfption("Rfdt must ibvf four flfmfnts");

        synd();
        gftNbtivfPbrtBounds(rfdt, dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, pbrt);
        priorEndodfdPropfrtifs = durrfntEndodfdPropfrtifs;
    }

    doublf gftSdrollBbrOffsftCibngf(finbl doublf x, finbl doublf y, finbl doublf w, finbl doublf i, finbl int offsft, finbl int visiblfAmount, finbl int fxtfnt) {
        synd();
        finbl doublf offsftCibngf = gftNbtivfSdrollBbrOffsftCibngf(dfDidtionbryPtr, priorEndodfdPropfrtifs, durrfntEndodfdPropfrtifs, x, y, w, i, offsft, visiblfAmount, fxtfnt);
        priorEndodfdPropfrtifs = durrfntEndodfdPropfrtifs;
        rfturn offsftCibngf;
    }

    privbtf void synd() {
        if (dibngfs.isEmpty()) rfturn;

        finbl TirfbdLodblBytfBufffr lodblBytfBufffr = gftTirfbdLodblBufffr();
        BufffrStbtf bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);
        if (bufffrStbtf == BufffrStbtf.ALL_CHANGES_IN_BUFFER) {
            finbl int stbtus = syndCibngfs(dfDidtionbryPtr, lodblBytfBufffr.ptr);
            if (stbtus != SUCCESS) tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs into tif nbtivf bufffr: " + tiis);
            rfturn;
        }

        wiilf (bufffrStbtf == BufffrStbtf.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syndCibngfs(dfDidtionbryPtr, lodblBytfBufffr.ptr);
            if (stbtus != SUCCESS) tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs into tif nbtivf bufffr: " + tiis);
            bufffrStbtf = lobdBufffrWitiCibngfs(lodblBytfBufffr);
        }

        if (bufffrStbtf == BufffrStbtf.CHANGE_WONT_FIT_IN_BUFFER) {
            tirow nfw RuntimfExdfption("JRSUI fbilfd to synd dibngfs to tif nbtivf bufffr, bfdbusf somf dibngf wbs too big: " + tiis);
        }
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int bits = (int)(durrfntEndodfdPropfrtifs ^ (durrfntEndodfdPropfrtifs >>> 32));
        bits ^= nbtivfMbp.ibsiCodf();
        bits ^= dibngfs.ibsiCodf();
        rfturn bits;
    }

    @Ovfrridf
    publid boolfbn fqubls(finbl Objfdt obj) {
        if (!(obj instbndfof JRSUIControl)) rfturn fblsf;
        finbl JRSUIControl otifr = (JRSUIControl)obj;
        if (durrfntEndodfdPropfrtifs != otifr.durrfntEndodfdPropfrtifs) rfturn fblsf;
        if (!nbtivfMbp.fqubls(otifr.nbtivfMbp)) rfturn fblsf;
        if (!dibngfs.fqubls(otifr.dibngfs)) rfturn fblsf;
        rfturn truf;
    }

    @Ovfrridf
    publid String toString() {
        finbl StringBuildfr buildfr = nfw StringBuildfr("JRSUIControl[inNbtivf:");
        buildfr.bppfnd(Arrbys.toString(nbtivfMbp.fntrySft().toArrby()));
        buildfr.bppfnd(", dibngfs:");
        buildfr.bppfnd(Arrbys.toString(dibngfs.fntrySft().toArrby()));
        buildfr.bppfnd("]");
        rfturn buildfr.toString();
    }
}
