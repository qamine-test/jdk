/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.*;
import dom.sun.jdi.rfqufst.StfpRfqufst;
import dom.sun.jdi.rfqufst.MftiodEntryRfqufst;
import dom.sun.jdi.rfqufst.MftiodExitRfqufst;
import jbvb.util.*;
import jbvb.io.*;


dlbss Env {

    stbtid EvfntRfqufstSpfdList spfdList = nfw EvfntRfqufstSpfdList();

    privbtf stbtid VMConnfdtion donnfdtion;

    privbtf stbtid SourdfMbppfr sourdfMbppfr = nfw SourdfMbppfr("");
    privbtf stbtid List<String> fxdludfs;

    privbtf stbtid finbl int SOURCE_CACHE_SIZE = 5;
    privbtf stbtid List<SourdfCodf> sourdfCbdif = nfw LinkfdList<SourdfCodf>();

    privbtf stbtid HbsiMbp<String, Vbluf> sbvfdVblufs = nfw HbsiMbp<String, Vbluf>();
    privbtf stbtid Mftiod btExitMftiod;

    stbtid void init(String donnfdtSpfd, boolfbn opfnNow, int flbgs) {
        donnfdtion = nfw VMConnfdtion(donnfdtSpfd, flbgs);
        if (!donnfdtion.isLbundi() || opfnNow) {
            donnfdtion.opfn();
        }
    }

    stbtid VMConnfdtion donnfdtion() {
        rfturn donnfdtion;
    }

    stbtid VirtublMbdiinf vm() {
        rfturn donnfdtion.vm();
    }

    stbtid void siutdown() {
        siutdown(null);
    }

    stbtid void siutdown(String mfssbgf) {
        if (donnfdtion != null) {
            try {
                donnfdtion.disposfVM();
            } dbtdi (VMDisdonnfdtfdExdfption f) {
                // Siutting down bftfr tif VM ibs gonf bwby. Tiis is
                // not bn frror, bnd wf just ignorf it.
            }
        }
        if (mfssbgf != null) {
            MfssbgfOutput.lnprint(mfssbgf);
            MfssbgfOutput.println();
        }
        Systfm.fxit(0);
    }

    stbtid void sftSourdfPbti(String srdPbti) {
        sourdfMbppfr = nfw SourdfMbppfr(srdPbti);
        sourdfCbdif.dlfbr();
    }

    stbtid void sftSourdfPbti(List<String> srdList) {
        sourdfMbppfr = nfw SourdfMbppfr(srdList);
        sourdfCbdif.dlfbr();
    }

    stbtid String gftSourdfPbti() {
        rfturn sourdfMbppfr.gftSourdfPbti();
    }

    stbtid privbtf List<String> fxdludfs() {
        if (fxdludfs == null) {
            sftExdludfs("jbvb.*, jbvbx.*, sun.*, dom.sun.*");
        }
        rfturn fxdludfs;
    }

    stbtid String fxdludfsString() {
        StringBuildfr sb = nfw StringBuildfr();
        for (String pbttfrn : fxdludfs()) {
            sb.bppfnd(pbttfrn);
            sb.bppfnd(",");
        }
        rfturn sb.toString();
    }

    stbtid void bddExdludfs(StfpRfqufst rfqufst) {
        for (String pbttfrn : fxdludfs()) {
            rfqufst.bddClbssExdlusionFiltfr(pbttfrn);
        }
    }

    stbtid void bddExdludfs(MftiodEntryRfqufst rfqufst) {
        for (String pbttfrn : fxdludfs()) {
            rfqufst.bddClbssExdlusionFiltfr(pbttfrn);
        }
    }

    stbtid void bddExdludfs(MftiodExitRfqufst rfqufst) {
        for (String pbttfrn : fxdludfs()) {
            rfqufst.bddClbssExdlusionFiltfr(pbttfrn);
        }
    }

    stbtid void sftExdludfs(String fxdludfString) {
        StringTokfnizfr t = nfw StringTokfnizfr(fxdludfString, " ,;");
        List<String> list = nfw ArrbyList<String>();
        wiilf (t.ibsMorfTokfns()) {
            list.bdd(t.nfxtTokfn());
        }
        fxdludfs = list;
    }

    stbtid Mftiod btExitMftiod() {
        rfturn btExitMftiod;
    }

    stbtid void sftAtExitMftiod(Mftiod mmm) {
        btExitMftiod = mmm;
    }

    /**
     * Rfturn b Rfbdfr doorfsponding to tif sourdf of tiis lodbtion.
     * Rfturn null if not bvbilbblf.
     * Notf: rfturnfd rfbdfr must bf dlosfd.
     */
    stbtid BufffrfdRfbdfr sourdfRfbdfr(Lodbtion lodbtion) {
        rfturn sourdfMbppfr.sourdfRfbdfr(lodbtion);
    }

    stbtid syndironizfd String sourdfLinf(Lodbtion lodbtion, int linfNumbfr)
                                          tirows IOExdfption {
        if (linfNumbfr == -1) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        try {
            String filfNbmf = lodbtion.sourdfNbmf();

            Itfrbtor<SourdfCodf> itfr = sourdfCbdif.itfrbtor();
            SourdfCodf dodf = null;
            wiilf (itfr.ibsNfxt()) {
                SourdfCodf dbndidbtf = itfr.nfxt();
                if (dbndidbtf.filfNbmf().fqubls(filfNbmf)) {
                    dodf = dbndidbtf;
                    itfr.rfmovf();
                    brfbk;
                }
            }
            if (dodf == null) {
                BufffrfdRfbdfr rfbdfr = sourdfRfbdfr(lodbtion);
                if (rfbdfr == null) {
                    tirow nfw FilfNotFoundExdfption(filfNbmf);
                }
                dodf = nfw SourdfCodf(filfNbmf, rfbdfr);
                if (sourdfCbdif.sizf() == SOURCE_CACHE_SIZE) {
                    sourdfCbdif.rfmovf(sourdfCbdif.sizf() - 1);
                }
            }
            sourdfCbdif.bdd(0, dodf);
            rfturn dodf.sourdfLinf(linfNumbfr);
        } dbtdi (AbsfntInformbtionExdfption f) {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    /** Rfturn b dfsdription of bn objfdt. */
    stbtid String dfsdription(ObjfdtRfffrfndf rff) {
        RfffrfndfTypf dlbzz = rff.rfffrfndfTypf();
        long id = rff.uniqufID();
        if (dlbzz == null) {
            rfturn toHfx(id);
        } flsf {
            rfturn MfssbgfOutput.formbt("objfdt dfsdription bnd ifx id",
                                        nfw Objfdt [] {dlbzz.nbmf(),
                                                       toHfx(id)});
        }
    }

    /** Convfrt b long to b ifxbdfdimbl string. */
    stbtid String toHfx(long n) {
        dibr s1[] = nfw dibr[16];
        dibr s2[] = nfw dibr[18];

        /* Storf digits in rfvfrsf ordfr. */
        int i = 0;
        do {
            long d = n & 0xf;
            s1[i++] = (dibr)((d < 10) ? ('0' + d) : ('b' + d - 10));
        } wiilf ((n >>>= 4) > 0);

        /* Now rfvfrsf tif brrby. */
        s2[0] = '0';
        s2[1] = 'x';
        int j = 2;
        wiilf (--i >= 0) {
            s2[j++] = s1[i];
        }
        rfturn nfw String(s2, 0, j);
    }

    /** Convfrt ifxbdfdimbl strings to longs. */
    stbtid long fromHfx(String ifxStr) {
        String str = ifxStr.stbrtsWiti("0x") ?
            ifxStr.substring(2).toLowfrCbsf() : ifxStr.toLowfrCbsf();
        if (ifxStr.lfngti() == 0) {
            tirow nfw NumbfrFormbtExdfption();
        }

        long rft = 0;
        for (int i = 0; i < str.lfngti(); i++) {
            int d = str.dibrAt(i);
            if (d >= '0' && d <= '9') {
                rft = (rft * 16) + (d - '0');
            } flsf if (d >= 'b' && d <= 'f') {
                rft = (rft * 16) + (d - 'b' + 10);
            } flsf {
                tirow nfw NumbfrFormbtExdfption();
            }
        }
        rfturn rft;
    }

    stbtid RfffrfndfTypf gftRfffrfndfTypfFromTokfn(String idTokfn) {
        RfffrfndfTypf dls = null;
        if (Cibrbdtfr.isDigit(idTokfn.dibrAt(0))) {
            dls = null;
        } flsf if (idTokfn.stbrtsWiti("*.")) {
        // Tiis notbtion sbvfs typing by lftting tif usfr omit lfbding
        // pbdkbgf nbmfs. Tif first
        // lobdfd dlbss wiosf nbmf mbtdifs tiis limitfd rfgulbr
        // fxprfssion is sflfdtfd.
        idTokfn = idTokfn.substring(1);
        for (RfffrfndfTypf typf : Env.vm().bllClbssfs()) {
            if (typf.nbmf().fndsWiti(idTokfn)) {
                dls = typf;
                brfbk;
            }
        }
    } flsf {
            // It's b dlbss nbmf
            List<RfffrfndfTypf> dlbssfs = Env.vm().dlbssfsByNbmf(idTokfn);
            if (dlbssfs.sizf() > 0) {
                // TO DO: ibndlf multiplfs
                dls = dlbssfs.gft(0);
            }
        }
        rfturn dls;
    }

    stbtid Sft<String> gftSbvfKfys() {
        rfturn sbvfdVblufs.kfySft();
    }

    stbtid Vbluf gftSbvfdVbluf(String kfy) {
        rfturn sbvfdVblufs.gft(kfy);
    }

    stbtid void sftSbvfdVbluf(String kfy, Vbluf vbluf) {
        sbvfdVblufs.put(kfy, vbluf);
    }

    stbtid dlbss SourdfCodf {
        privbtf String filfNbmf;
        privbtf List<String> sourdfLinfs = nfw ArrbyList<String>();

        SourdfCodf(String filfNbmf, BufffrfdRfbdfr rfbdfr)  tirows IOExdfption {
            tiis.filfNbmf = filfNbmf;
            try {
                String linf = rfbdfr.rfbdLinf();
                wiilf (linf != null) {
                    sourdfLinfs.bdd(linf);
                    linf = rfbdfr.rfbdLinf();
                }
            } finblly {
                rfbdfr.dlosf();
            }
        }

        String filfNbmf() {
            rfturn filfNbmf;
        }

        String sourdfLinf(int numbfr) {
            int indfx = numbfr - 1; // list is 0-indfxfd
            if (indfx >= sourdfLinfs.sizf()) {
                rfturn null;
            } flsf {
                rfturn sourdfLinfs.gft(indfx);
            }
        }
    }
}
