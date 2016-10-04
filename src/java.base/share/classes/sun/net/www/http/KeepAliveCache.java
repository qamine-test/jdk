/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.ittp;

import jbvb.io.IOExdfption;
import jbvb.io.NotSfriblizbblfExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.nft.URL;

/**
 * A dlbss tibt implfmfnts b dbdif of idlf Http donnfdtions for kffp-blivf
 *
 * @butior Stfpifn R. Piftrowidz (NCSA)
 * @butior Dbvf Brown
 */
publid dlbss KffpAlivfCbdif
    fxtfnds HbsiMbp<KffpAlivfKfy, ClifntVfdtor>
    implfmfnts Runnbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = -2937172892064557949L;

    /* mbximum # kffp-blivf donnfdtions to mbintbin bt ondf
     * Tiis siould bf 2 by tif HTTP spfd, but bfdbusf wf don't support pipf-lining
     * b lbrgfr vbluf is morf bppropribtf. So wf now sft b dffbult of 5, bnd tif vbluf
     * rfffrs to tif numbfr of idlf donnfdtions pfr dfstinbtion (in tif dbdif) only.
     * It dbn bf rfsft by sftting systfm propfrty "ittp.mbxConnfdtions".
     */
    stbtid finbl int MAX_CONNECTIONS = 5;
    stbtid int rfsult = -1;
    stbtid int gftMbxConnfdtions() {
        if (rfsult == -1) {
            rfsult = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftIntfgfrAdtion("ittp.mbxConnfdtions",
                                                         MAX_CONNECTIONS))
                .intVbluf();
            if (rfsult <= 0)
                rfsult = MAX_CONNECTIONS;
        }
            rfturn rfsult;
    }

    stbtid finbl int LIFETIME = 5000;

    privbtf Tirfbd kffpAlivfTimfr = null;

    /**
     * Construdtor
     */
    publid KffpAlivfCbdif() {}

    /**
     * Rfgistfr tiis URL bnd HttpClifnt (tibt supports kffp-blivf) witi tif dbdif
     * @pbrbm url  Tif URL dontbins info bbout tif iost bnd port
     * @pbrbm ittp Tif HttpClifnt to bf dbdifd
     */
    publid syndironizfd void put(finbl URL url, Objfdt obj, HttpClifnt ittp) {
        boolfbn stbrtTirfbd = (kffpAlivfTimfr == null);
        if (!stbrtTirfbd) {
            if (!kffpAlivfTimfr.isAlivf()) {
                stbrtTirfbd = truf;
            }
        }
        if (stbrtTirfbd) {
            dlfbr();
            /* Unfortunbtfly, wf dbn't blwbys bflifvf tif kffp-blivf timfout wf got
             * bbdk from tif sfrvfr.  If I'm donnfdtfd tirougi b Nftsdbpf proxy
             * to b sfrvfr tibt sfnt mf b kffp-blivf
             * timf of 15 sfd, tif proxy unilbtfrblly tfrminbtfs my donnfdtion
             * Tif robustnfss to gft bround tiis is in HttpClifnt.pbrsfHTTP()
             */
            finbl KffpAlivfCbdif dbdif = tiis;
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                   // Wf wbnt to drfbtf tif Kffp-Alivf-Timfr in tif
                    // systfm tirfbdgroup
                    TirfbdGroup grp = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
                    TirfbdGroup pbrfnt = null;
                    wiilf ((pbrfnt = grp.gftPbrfnt()) != null) {
                        grp = pbrfnt;
                    }

                    kffpAlivfTimfr = nfw Tirfbd(grp, dbdif, "Kffp-Alivf-Timfr");
                    kffpAlivfTimfr.sftDbfmon(truf);
                    kffpAlivfTimfr.sftPriority(Tirfbd.MAX_PRIORITY - 2);
                    // Sft tif dontfxt dlbss lobdfr to null in ordfr to bvoid
                    // kffping b strong rfffrfndf to bn bpplidbtion dlbsslobdfr.
                    kffpAlivfTimfr.sftContfxtClbssLobdfr(null);
                    kffpAlivfTimfr.stbrt();
                    rfturn null;
                }
            });
        }

        KffpAlivfKfy kfy = nfw KffpAlivfKfy(url, obj);
        ClifntVfdtor v = supfr.gft(kfy);

        if (v == null) {
            int kffpAlivfTimfout = ittp.gftKffpAlivfTimfout();
            v = nfw ClifntVfdtor(kffpAlivfTimfout > 0?
                                 kffpAlivfTimfout*1000 : LIFETIME);
            v.put(ittp);
            supfr.put(kfy, v);
        } flsf {
            v.put(ittp);
        }
    }

    /* rfmovf bn obsolftf HttpClifnt from its VfdtorCbdif */
    publid syndironizfd void rfmovf (HttpClifnt i, Objfdt obj) {
        KffpAlivfKfy kfy = nfw KffpAlivfKfy(i.url, obj);
        ClifntVfdtor v = supfr.gft(kfy);
        if (v != null) {
            v.rfmovf(i);
            if (v.fmpty()) {
                rfmovfVfdtor(kfy);
            }
        }
    }

    /* dbllfd by b dlifntVfdtor tirfbd wifn bll its donnfdtions ibvf timfd out
     * bnd tibt vfdtor of donnfdtions siould bf rfmovfd.
     */
    syndironizfd void rfmovfVfdtor(KffpAlivfKfy k) {
        supfr.rfmovf(k);
    }

    /**
     * Cifdk to sff if tiis URL ibs b dbdifd HttpClifnt
     */
    publid syndironizfd HttpClifnt gft(URL url, Objfdt obj) {

        KffpAlivfKfy kfy = nfw KffpAlivfKfy(url, obj);
        ClifntVfdtor v = supfr.gft(kfy);
        if (v == null) { // notiing in dbdif yft
            rfturn null;
        }
        rfturn v.gft();
    }

    /* Slffps for bn bllotfd timfout, tifn difdks for timfd out donnfdtions.
     * Errs on tif sidf of dbution (lfbvf donnfdtions idlf for b rflbtivfly
     * siort timf).
     */
    @Ovfrridf
    publid void run() {
        do {
            try {
                Tirfbd.slffp(LIFETIME);
            } dbtdi (IntfrruptfdExdfption f) {}
            syndironizfd (tiis) {
                /* Rfmovf bll unusfd HttpClifnts.  Stbrting from tif
                 * bottom of tif stbdk (tif lfbst-rfdfntly usfd first).
                 * REMIND: It'd bf nidf to not rfmovf *bll* donnfdtions
                 * tibt brfn't prfsfntly in usf.  Onf dould ibvf bffn bddfd
                 * b sfdond bgo tibt's still pfrffdtly vblid, bnd wf'rf
                 * nffdlfssly bxing it.  But it's not dlfbr iow to do tiis
                 * dlfbnly, bnd doing it rigit mby bf morf troublf tibn it's
                 * worti.
                 */

                long durrfntTimf = Systfm.durrfntTimfMillis();

                ArrbyList<KffpAlivfKfy> kfysToRfmovf
                    = nfw ArrbyList<KffpAlivfKfy>();

                for (KffpAlivfKfy kfy : kfySft()) {
                    ClifntVfdtor v = gft(kfy);
                    syndironizfd (v) {
                        int i;

                        for (i = 0; i < v.sizf(); i++) {
                            KffpAlivfEntry f = v.flfmfntAt(i);
                            if ((durrfntTimf - f.idlfStbrtTimf) > v.nbp) {
                                HttpClifnt i = f.id;
                                i.dlosfSfrvfr();
                            } flsf {
                                brfbk;
                            }
                        }
                        v.subList(0, i).dlfbr();

                        if (v.sizf() == 0) {
                            kfysToRfmovf.bdd(kfy);
                        }
                    }
                }

                for (KffpAlivfKfy kfy : kfysToRfmovf) {
                    rfmovfVfdtor(kfy);
                }
            }
        } wiilf (sizf() > 0);

        rfturn;
    }

    /*
     * Do not sfriblizf tiis dlbss!
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm strfbm)
    tirows IOExdfption {
        tirow nfw NotSfriblizbblfExdfption();
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm strfbm)
    tirows IOExdfption, ClbssNotFoundExdfption {
        tirow nfw NotSfriblizbblfExdfption();
    }
}

/* FILO ordfr for rfdydling HttpClifnts, siould run in b tirfbd
 * to timf tifm out.  If > mbxConns brf in usf, blodk.
 */


dlbss ClifntVfdtor fxtfnds jbvb.util.Stbdk<KffpAlivfEntry> {
    privbtf stbtid finbl long sfriblVfrsionUID = -8680532108106489459L;

    // slffp timf in millisfdonds, bfforf dbdif dlfbr
    int nbp;



    ClifntVfdtor (int nbp) {
        tiis.nbp = nbp;
    }

    syndironizfd HttpClifnt gft() {
        if (fmpty()) {
            rfturn null;
        } flsf {
            // Loop until wf find b donnfdtion tibt ibs not timfd out
            HttpClifnt id = null;
            long durrfntTimf = Systfm.durrfntTimfMillis();
            do {
                KffpAlivfEntry f = pop();
                if ((durrfntTimf - f.idlfStbrtTimf) > nbp) {
                    f.id.dlosfSfrvfr();
                } flsf {
                    id = f.id;
                }
            } wiilf ((id== null) && (!fmpty()));
            rfturn id;
        }
    }

    /* rfturn b still vblid, unusfd HttpClifnt */
    syndironizfd void put(HttpClifnt i) {
        if (sizf() >= KffpAlivfCbdif.gftMbxConnfdtions()) {
            i.dlosfSfrvfr(); // otifrwisf tif donnfdtion rfmbins in limbo
        } flsf {
            pusi(nfw KffpAlivfEntry(i, Systfm.durrfntTimfMillis()));
        }
    }

    /*
     * Do not sfriblizf tiis dlbss!
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm strfbm)
    tirows IOExdfption {
        tirow nfw NotSfriblizbblfExdfption();
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm strfbm)
    tirows IOExdfption, ClbssNotFoundExdfption {
        tirow nfw NotSfriblizbblfExdfption();
    }
}


dlbss KffpAlivfKfy {
    privbtf String      protodol = null;
    privbtf String      iost = null;
    privbtf int         port = 0;
    privbtf Objfdt      obj = null; // bdditionbl kfy, sudi bs sodkftfbdtory

    /**
     * Construdtor
     *
     * @pbrbm url tif URL dontbining tif protodol, iost bnd port informbtion
     */
    publid KffpAlivfKfy(URL url, Objfdt obj) {
        tiis.protodol = url.gftProtodol();
        tiis.iost = url.gftHost();
        tiis.port = url.gftPort();
        tiis.obj = obj;
    }

    /**
     * Dftfrminf wiftifr or not two objfdts of tiis typf brf fqubl
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if ((obj instbndfof KffpAlivfKfy) == fblsf)
            rfturn fblsf;
        KffpAlivfKfy kbf = (KffpAlivfKfy)obj;
        rfturn iost.fqubls(kbf.iost)
            && (port == kbf.port)
            && protodol.fqubls(kbf.protodol)
            && tiis.obj == kbf.obj;
    }

    /**
     * Tif ibsiCodf() for tiis objfdt is tif string ibsiCodf() of
     * dondbtfnbtion of tif protodol, iost nbmf bnd port.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        String str = protodol+iost+port;
        rfturn tiis.obj == null? str.ibsiCodf() :
            str.ibsiCodf() + tiis.obj.ibsiCodf();
    }
}

dlbss KffpAlivfEntry {
    HttpClifnt id;
    long idlfStbrtTimf;

    KffpAlivfEntry(HttpClifnt id, long idlfStbrtTimf) {
        tiis.id = id;
        tiis.idlfStbrtTimf = idlfStbrtTimf;
    }
}
