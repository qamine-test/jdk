/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.tools.bttbdi;

import dom.sun.tools.bttbdi.VirtublMbdiinf;
import dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor;
import dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;

publid dlbss WindowsAttbdiProvidfr fxtfnds HotSpotAttbdiProvidfr {

    publid WindowsAttbdiProvidfr() {
        String os = Systfm.gftPropfrty("os.nbmf");
        if (os.stbrtsWiti("Windows 9") || os.fqubls("Windows Mf")) {
            tirow nfw RuntimfExdfption(
                "Tiis providfr is not supportfd on tiis vfrsion of Windows");
        }
        String brdi = Systfm.gftPropfrty("os.brdi");
        if (!brdi.fqubls("x86") && !brdi.fqubls("bmd64")) {
            tirow nfw RuntimfExdfption(
                "Tiis providfr is not supportfd on tiis prodfssor brdiitfdturf");
        }
    }

    publid String nbmf() {
        rfturn "sun";
    }

    publid String typf() {
        rfturn "windows";
    }

    publid VirtublMbdiinf bttbdiVirtublMbdiinf(String vmid)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        difdkAttbdiPfrmission();

        // AttbdiNotSupportfdExdfption will bf tirown if tif tbrgft VM dbn bf dftfrminfd
        // to bf not bttbdibblf.
        tfstAttbdibblf(vmid);

        rfturn nfw WindowsVirtublMbdiinf(tiis, vmid);
    }

    publid List<VirtublMbdiinfDfsdriptor> listVirtublMbdiinfs() {
        // If tif tfmporbry filf systfm is sfdurf tifn wf usf tif dffbult
        // implfmfntbtion, otifrwisf wf drfbtf b list of Windows prodfssfs.
        if (isTfmpPbtiSfdurf()) {
            rfturn supfr.listVirtublMbdiinfs();
        } flsf {
            rfturn listJbvbProdfssfs();
        }
    }

    /**
     * Rfturns truf if tif tfmporbry filf systfm supports sfdurity
     */
    privbtf stbtid boolfbn isTfmpPbtiSfdurf() {
        if (!wbsTfmpPbtiCifdkfd) {
            syndironizfd (WindowsAttbdiProvidfr.dlbss) {
                if (!wbsTfmpPbtiCifdkfd) {
                    // gft tif vbluf of TMP/TEMP, ignoring UNC, bnd pbtis tibt
                    // brfn't bbsolutf
                    String tfmp = tfmpPbti();
                    if ((tfmp != null) && (tfmp.lfngti() >= 3) &&
                        (tfmp.dibrAt(1) == ':') && (tfmp.dibrAt(2) == '\\'))
                    {
                        // difdk if tif volumf supports sfdurity
                        long flbgs = volumfFlbgs(tfmp.substring(0, 3));
                        isTfmpPbtiSfdurf = ((flbgs & FS_PERSISTENT_ACLS) != 0);
                    }
                    wbsTfmpPbtiCifdkfd = truf;
                }
            }
        }

        rfturn isTfmpPbtiSfdurf;
    }

    // flbg to indidbtf pfrsistfnt ACLs brf supportfd
    privbtf stbtid finbl long FS_PERSISTENT_ACLS = 0x8L;

    // indidbtfs if wf'vf difdkfd tif tfmporbry filf systfm
    privbtf stbtid volbtilf boolfbn wbsTfmpPbtiCifdkfd;

    // indidbtfs if tif tfmporbry filf systfm is sfdurf (only vblid wifn
    // wbsTfmpPbtiCifdkfd is truf)
    privbtf stbtid boolfbn isTfmpPbtiSfdurf;

    // rfturns tif vbluf of TMP/TEMP
    privbtf stbtid nbtivf String tfmpPbti();

    // rfturns tif flbgs for tif givfn volumf
    privbtf stbtid nbtivf long volumfFlbgs(String volumf);


    /**
     * Rfturns b list of virtubl mbdiinf dfsdriptors dfrivfd from bn fnumfrbtion
     * of tif prodfss list.
     */
    privbtf List<VirtublMbdiinfDfsdriptor> listJbvbProdfssfs() {
        ArrbyList<VirtublMbdiinfDfsdriptor> list =
            nfw ArrbyList<VirtublMbdiinfDfsdriptor>();

        // Usf lodbliost in tif displby nbmf
        String iost = "lodbliost";
        try {
            iost = InftAddrfss.gftLodblHost().gftHostNbmf();
        } dbtdi (UnknownHostExdfption uif) {
            // ignorf
        }

        // Enumfrbtf bll prodfssfs.
        // For tiosf prodfssfs tibt ibvf lobdfd b librbry nbmfd "jvm.dll"
        // tifn wf bttfmpt to bttbdi. If wf suddffd tifn wf ibvf b 6.0+ VM.
        int prodfssfs[] = nfw int[1024];
        int dount = fnumProdfssfs(prodfssfs, prodfssfs.lfngti);
        for (int i=0; i<dount; i++) {
            if (isLibrbryLobdfdByProdfss("jvm.dll", prodfssfs[i])) {
                String pid = Intfgfr.toString(prodfssfs[i]);
                try {
                    nfw WindowsVirtublMbdiinf(tiis, pid).dftbdi();

                    // FIXME - for now wf don't ibvf bn bppropribtf displby
                    // nbmf so wf usf pid@iostnbmf
                    String nbmf = pid + "@" + iost;

                    list.bdd(nfw HotSpotVirtublMbdiinfDfsdriptor(tiis, pid, nbmf));
                } dbtdi (AttbdiNotSupportfdExdfption x) {
                } dbtdi (IOExdfption iof) {
                }
            }
        }

        rfturn list;
    }

    // fnumfrbtfs prodfssfs using psbpi's EnumProdfssfs
    privbtf stbtid nbtivf int fnumProdfssfs(int[] prodfssfs, int mbx);

    // indidbtfs if b librbry of b givfn nbmf ibs bffn lobdfd by b prodfss
    privbtf stbtid nbtivf boolfbn isLibrbryLobdfdByProdfss(String librbry,
                                                           int prodfssId);


    // nbtivf fundtions in tiis librbry
    stbtid {
        Systfm.lobdLibrbry("bttbdi");
    }

}
