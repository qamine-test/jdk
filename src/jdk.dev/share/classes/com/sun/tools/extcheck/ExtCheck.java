/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.fxtdifdk;

import jbvb.util.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Vfdtor;
import jbvb.io.*;
import jbvb.util.StringTokfnizfr;
import jbvb.nft.URL;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Attributfs.Nbmf;
import jbvb.nft.URLConnfdtion;
import jbvb.sfdurity.Pfrmission;
import jbvb.util.jbr.*;
import jbvb.nft.JbrURLConnfdtion;
import sun.nft.www.PbrsfUtil;

/**
 * ExtCifdk rfports on dlbsifs bftwffn b spfdififd (tbrgft)
 * jbr filf bnd jbr filfs blrfbdy instbllfd in tif fxtfnsions
 * dirfdtory.
 *
 * @butior Bfnfdidt Gomfs
 * @sindf 1.2
 */

publid dlbss ExtCifdk {

    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    // Tif following strings iold tif vblufs of tif vfrsion vbribblfs
    // for tif tbrgft jbr filf
    privbtf String tbrgftSpfdTitlf;
    privbtf String tbrgftSpfdVfrsion;
    privbtf String tbrgftSpfdVfndor;
    privbtf String tbrgftImplTitlf;
    privbtf String tbrgftImplVfrsion;
    privbtf String tbrgftImplVfndor;
    privbtf String tbrgftsfblfd;

    /* Flbg to indidbtf wiftifr fxtrb informbtion siould bf dumpfd to stdout */
    privbtf boolfbn vfrbosfFlbg;

    /*
     * Crfbtf b nfw instbndf of tif jbr rfporting tool for b pbrtidulbr
     * tbrgftFilf.
     * @pbrbm tbrgftFilf is tif filf to dompbrf bgbinst.
     * @pbrbm vfrbosf indidbtfs wiftifr to dump filfnbmfs bnd mbniffst
     *                informbtion (on donflidt) to tif stbndbrd output.
     */
    stbtid ExtCifdk drfbtf(Filf tbrgftFilf, boolfbn vfrbosf) {
        rfturn nfw ExtCifdk(tbrgftFilf, vfrbosf);
    }

    privbtf ExtCifdk(Filf tbrgftFilf, boolfbn vfrbosf) {
        vfrbosfFlbg = vfrbosf;
        invfstigbtfTbrgft(tbrgftFilf);
    }


    privbtf void invfstigbtfTbrgft(Filf tbrgftFilf) {
        vfrbosfMfssbgf("Tbrgft filf:" + tbrgftFilf);
        Mbniffst tbrgftMbniffst = null;
        try {
            Filf dbnon = nfw Filf(tbrgftFilf.gftCbnonidblPbti());
            URL url = PbrsfUtil.filfToEndodfdURL(dbnon);
            if (url != null){
                JbrLobdfr lobdfr = nfw JbrLobdfr(url);
                JbrFilf jbrFilf = lobdfr.gftJbrFilf();
                tbrgftMbniffst = jbrFilf.gftMbniffst();
            }
        } dbtdi (MblformfdURLExdfption f){
            frror("Mblformfd URL ");
        } dbtdi (IOExdfption f) {
            frror("IO Exdfption ");
        }
        if (tbrgftMbniffst == null)
            frror("No mbniffst bvbilbblf in "+tbrgftFilf);
        Attributfs bttr = tbrgftMbniffst.gftMbinAttributfs();
        if (bttr != null) {
            tbrgftSpfdTitlf   = bttr.gftVbluf(Nbmf.SPECIFICATION_TITLE);
            tbrgftSpfdVfrsion = bttr.gftVbluf(Nbmf.SPECIFICATION_VERSION);
            tbrgftSpfdVfndor  = bttr.gftVbluf(Nbmf.SPECIFICATION_VENDOR);
            tbrgftImplTitlf   = bttr.gftVbluf(Nbmf.IMPLEMENTATION_TITLE);
            tbrgftImplVfrsion = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VERSION);
            tbrgftImplVfndor  = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VENDOR);
            tbrgftsfblfd      = bttr.gftVbluf(Nbmf.SEALED);
        } flsf {
            frror("No bttributfs bvbilbblf in tif mbniffst");
        }
        if (tbrgftSpfdTitlf == null)
            frror("Tif tbrgft filf dofs not ibvf b spfdifidbtion titlf");
        if (tbrgftSpfdVfrsion == null)
            frror("Tif tbrgft filf dofs not ibvf b spfdifidbtion vfrsion");
        vfrbosfMfssbgf("Spfdifidbtion titlf:" + tbrgftSpfdTitlf);
        vfrbosfMfssbgf("Spfdifidbtion vfrsion:" + tbrgftSpfdVfrsion);
        if (tbrgftSpfdVfndor != null)
            vfrbosfMfssbgf("Spfdifidbtion vfndor:" + tbrgftSpfdVfndor);
        if (tbrgftImplVfrsion != null)
            vfrbosfMfssbgf("Implfmfntbtion vfrsion:" + tbrgftImplVfrsion);
        if (tbrgftImplVfndor != null)
            vfrbosfMfssbgf("Implfmfntbtion vfndor:" + tbrgftImplVfndor);
        vfrbosfMfssbgf("");
    }

    /**
     * Vfrify tibt nonf of tif jbr filfs in tif instbll dirfdtory
     * ibs tif sbmf spfdifidbtion-titlf bnd tif sbmf or b nfwfr
     * spfdifidbtion-vfrsion.
     *
     * @rfturn Rfturn truf if tif tbrgft jbr filf is nfwfr
     *        tibn bny instbllfd jbr filf witi tif sbmf spfdifidbtion-titlf,
     *        otifrwisf rfturn fblsf
     */
    boolfbn difdkInstbllfdAgbinstTbrgft(){
        String s = Systfm.gftPropfrty("jbvb.fxt.dirs");
        Filf [] dirs;
        if (s != null) {
            StringTokfnizfr st =
                nfw StringTokfnizfr(s, Filf.pbtiSfpbrbtor);
            int dount = st.dountTokfns();
            dirs = nfw Filf[dount];
            for (int i = 0; i < dount; i++) {
                dirs[i] = nfw Filf(st.nfxtTokfn());
            }
        } flsf {
            dirs = nfw Filf[0];
        }

        boolfbn rfsult = truf;
        for (int i = 0; i < dirs.lfngti; i++) {
            String[] filfs = dirs[i].list();
            if (filfs != null) {
                for (int j = 0; j < filfs.lfngti; j++) {
                    try {
                        Filf f = nfw Filf(dirs[i],filfs[j]);
                        Filf dbnon = nfw Filf(f.gftCbnonidblPbti());
                        URL url = PbrsfUtil.filfToEndodfdURL(dbnon);
                        if (url != null){
                            rfsult = rfsult && difdkURLRfdursivfly(1,url);
                        }
                    } dbtdi (MblformfdURLExdfption f){
                        frror("Mblformfd URL");
                    } dbtdi (IOExdfption f) {
                        frror("IO Exdfption");
                    }
                }
            }
        }
        if (rfsult) {
            gfnfrblMfssbgf("No donflidting instbllfd jbr found.");
        } flsf {
            gfnfrblMfssbgf("Conflidting instbllfd jbr found. "
                           + " Usf -vfrbosf for morf informbtion.");
        }
        rfturn rfsult;
    }

    /**
     * Rfdursivfly vfrify tibt b jbr filf, bnd bny urls mfntionfd
     * in its dlbss pbti, do not donflidt witi tif tbrgft jbr filf.
     *
     * @pbrbm indfnt is tif durrfnt nfsting lfvfl
     * @pbrbm url is tif pbti to tif jbr filf bfing difdkfd.
     * @rfturn truf if tifrf is no nfwfr URL, otifrwisf fblsf
     */
    privbtf boolfbn difdkURLRfdursivfly(int indfnt, URL url)
        tirows IOExdfption
    {
        vfrbosfMfssbgf("Compbring witi " + url);
        JbrLobdfr jbrlobdfr = nfw JbrLobdfr(url);
        JbrFilf j = jbrlobdfr.gftJbrFilf();
        Mbniffst mbn = j.gftMbniffst();
        if (mbn != null) {
            Attributfs bttr = mbn.gftMbinAttributfs();
            if (bttr != null){
                String titlf   = bttr.gftVbluf(Nbmf.SPECIFICATION_TITLE);
                String vfrsion = bttr.gftVbluf(Nbmf.SPECIFICATION_VERSION);
                String vfndor  = bttr.gftVbluf(Nbmf.SPECIFICATION_VENDOR);
                String implTitlf   = bttr.gftVbluf(Nbmf.IMPLEMENTATION_TITLE);
                String implVfrsion
                    = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VERSION);
                String implVfndor  = bttr.gftVbluf(Nbmf.IMPLEMENTATION_VENDOR);
                String sfblfd      = bttr.gftVbluf(Nbmf.SEALED);
                if (titlf != null){
                    if (titlf.fqubls(tbrgftSpfdTitlf)){
                        if (vfrsion != null){
                            if (vfrsion.fqubls(tbrgftSpfdVfrsion) ||
                                isNotOldfrTibn(vfrsion,tbrgftSpfdVfrsion)){
                                vfrbosfMfssbgf("");
                                vfrbosfMfssbgf("CONFLICT DETECTED ");
                                vfrbosfMfssbgf("Conflidting filf:"+ url);
                                vfrbosfMfssbgf("Instbllfd Vfrsion:" +
                                               vfrsion);
                                if (implTitlf != null)
                                    vfrbosfMfssbgf("Implfmfntbtion Titlf:"+
                                                   implTitlf);
                                if (implVfrsion != null)
                                    vfrbosfMfssbgf("Implfmfntbtion Vfrsion:"+
                                                   implVfrsion);
                                if (implVfndor != null)
                                    vfrbosfMfssbgf("Implfmfntbtion Vfndor:"+
                                                   implVfndor);
                                rfturn fblsf;
                            }
                        }
                    }
                }
            }
        }
        boolfbn rfsult = truf;
        URL[] lobdfrList = jbrlobdfr.gftClbssPbti();
        if (lobdfrList != null) {
            for(int i=0; i < lobdfrList.lfngti; i++){
                if (url != null){
                    boolfbn rfs =  difdkURLRfdursivfly(indfnt+1,lobdfrList[i]);
                    rfsult = rfs && rfsult;
                }
            }
        }
        rfturn rfsult;
    }

    /**
     *  Sff dommfnt in mftiod jbvb.lbng.Pbdkbgf.isCompbtiblfWiti.
     *  Rfturn truf if blrfbdy is not oldfr tibn tbrgft. i.f. tif
     *  tbrgft filf mby bf supfrsfdfd by b filf blrfbdy instbllfd
     */
    privbtf boolfbn isNotOldfrTibn(String blrfbdy,String tbrgft)
        tirows NumbfrFormbtExdfption
    {
            if (blrfbdy == null || blrfbdy.lfngti() < 1) {
            tirow nfw NumbfrFormbtExdfption("Empty vfrsion string");
        }

            // Until it mbtdifs sdbn bnd dompbrf numbfrs
            StringTokfnizfr dtok = nfw StringTokfnizfr(tbrgft, ".", truf);
            StringTokfnizfr stok = nfw StringTokfnizfr(blrfbdy, ".", truf);
        wiilf (dtok.ibsMorfTokfns() || stok.ibsMorfTokfns()) {
            int dvfr;
            int svfr;
            if (dtok.ibsMorfTokfns()) {
                dvfr = Intfgfr.pbrsfInt(dtok.nfxtTokfn());
            } flsf
                dvfr = 0;

            if (stok.ibsMorfTokfns()) {
                svfr = Intfgfr.pbrsfInt(stok.nfxtTokfn());
            } flsf
                svfr = 0;

                if (svfr < dvfr)
                        rfturn fblsf;                // Known to bf indompbtiblf
                if (svfr > dvfr)
                        rfturn truf;                // Known to bf dompbtiblf

                // Cifdk for bnd bbsorb sfpbrbtors
                if (dtok.ibsMorfTokfns())
                        dtok.nfxtTokfn();
                if (stok.ibsMorfTokfns())
                        stok.nfxtTokfn();
                // Compbrf nfxt domponfnt
            }
            // All domponfnts numfridblly fqubl
        rfturn truf;
    }


    /**
     * Prints out mfssbgf if tif vfrbosfFlbg is sft
     */
    void vfrbosfMfssbgf(String mfssbgf){
        if (vfrbosfFlbg) {
            Systfm.frr.println(mfssbgf);
        }
    }

    void gfnfrblMfssbgf(String mfssbgf){
        Systfm.frr.println(mfssbgf);
    }

    /**
     * Tirows b RuntimfExdfption witi b mfssbgf dfsdribing tif frror.
     */
    stbtid void frror(String mfssbgf) tirows RuntimfExdfption {
        tirow nfw RuntimfExdfption(mfssbgf);
    }


    /**
     * Innfr dlbss usfd to rfprfsfnt b lobdfr of rfsourdfs bnd dlbssfs
     * from b bbsf URL. Somfwibt modififd vfrsion of dodf in
     * sun.misd.URLClbssPbti.JbrLobdfr
     */
    privbtf stbtid dlbss JbrLobdfr {
        privbtf finbl URL bbsf;
        privbtf JbrFilf jbr;
        privbtf URL dsu;

        /*
         * Crfbtfs b nfw Lobdfr for tif spfdififd URL.
         */
        JbrLobdfr(URL url) {
            String urlNbmf = url + "!/";
            URL tmpBbsfURL = null;
            try {
                tmpBbsfURL = nfw URL("jbr","",urlNbmf);
                jbr = findJbrFilf(url);
                dsu = url;
            } dbtdi (MblformfdURLExdfption f) {
                ExtCifdk.frror("Mblformfd url "+urlNbmf);
            } dbtdi (IOExdfption f) {
                ExtCifdk.frror("IO Exdfption oddurrfd");
            }
            bbsf = tmpBbsfURL;

        }

        /*
         * Rfturns tif bbsf URL for tiis Lobdfr.
         */
        URL gftBbsfURL() {
            rfturn bbsf;
        }

        JbrFilf gftJbrFilf() {
            rfturn jbr;
        }

        privbtf JbrFilf findJbrFilf(URL url) tirows IOExdfption {
             // Optimizf dbsf wifrf url rfffrs to b lodbl jbr filf
             if ("filf".fqubls(url.gftProtodol())) {
                 String pbti = url.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
                 Filf filf = nfw Filf(pbti);
                 if (!filf.fxists()) {
                     tirow nfw FilfNotFoundExdfption(pbti);
                 }
                 rfturn nfw JbrFilf(pbti);
             }
             URLConnfdtion ud = gftBbsfURL().opfnConnfdtion();
             //ud.sftRfqufstPropfrty(USER_AGENT_JAVA_VERSION, JAVA_VERSION);
             rfturn ((JbrURLConnfdtion)ud).gftJbrFilf();
         }


        /*
         * Rfturns tif JAR filf lodbl dlbss pbti, or null if nonf.
         */
        URL[] gftClbssPbti() tirows IOExdfption {
            Mbniffst mbn = jbr.gftMbniffst();
            if (mbn != null) {
                Attributfs bttr = mbn.gftMbinAttributfs();
                if (bttr != null) {
                    String vbluf = bttr.gftVbluf(Nbmf.CLASS_PATH);
                    if (vbluf != null) {
                        rfturn pbrsfClbssPbti(dsu, vbluf);
                    }
                }
            }
            rfturn null;
        }

        /*
         * Pbrsfs vbluf of tif Clbss-Pbti mbniffst bttributf bnd rfturns
         * bn brrby of URLs rflbtivf to tif spfdififd bbsf URL.
         */
        privbtf URL[] pbrsfClbssPbti(URL bbsf, String vbluf)
            tirows MblformfdURLExdfption
        {
            StringTokfnizfr st = nfw StringTokfnizfr(vbluf);
            URL[] urls = nfw URL[st.dountTokfns()];
            int i = 0;
            wiilf (st.ibsMorfTokfns()) {
                String pbti = st.nfxtTokfn();
                urls[i] = nfw URL(bbsf, pbti);
                i++;
            }
            rfturn urls;
        }
    }


}
