/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nio.di.sdtp;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.SodkftAddrfss;
import jbvb.nio.dibnnfls.AlrfbdyBoundExdfption;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.nio.di.IOUtil;
import sun.nio.di.Nft;
import dom.sun.nio.sdtp.SdtpSodkftOption;
import stbtid dom.sun.nio.sdtp.SdtpStbndbrdSodkftOptions.*;

publid dlbss SdtpNft {
    privbtf stbtid finbl String osNbmf = AddfssControllfr.doPrivilfgfd(
        (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("os.nbmf"));

    /* -- Misdfllbnfous SCTP utilitifs -- */

    privbtf stbtid boolfbn IPv4MbppfdAddrfssfs() {
        if ("SunOS".fqubls(osNbmf)) {
            /* Solbris supports IPv4Mbppfd Addrfssfs witi bindx */
            rfturn truf;
        } /* flsf {  //otifr OS/implfmfntbtions  */

        /* lksdtp/linux rfquirfs Ipv4 bddrfssfs */
        rfturn fblsf;
    }

    stbtid boolfbn tirowAlrfbdyBoundExdfption() tirows IOExdfption {
        tirow nfw AlrfbdyBoundExdfption();
    }

    stbtid void listfn(int fd, int bbdklog) tirows IOExdfption {
        listfn0(fd, bbdklog);
    }

    stbtid int donnfdt(int fd, InftAddrfss rfmotf, int rfmotfPort)
            tirows IOExdfption {
        rfturn donnfdt0(fd, rfmotf, rfmotfPort);
    }

    stbtid void dlosf(int fd) tirows IOExdfption {
        dlosf0(fd);
    }

    stbtid void prfClosf(int fd) tirows IOExdfption {
        prfClosf0(fd);
    }

    /**
     * @pbrbm  onfToOnf
     *         if {@dodf truf} rfturns b onf-to-onf sdtp sodkft, otifrwisf
     *         rfturns b onf-to-mbny sdtp sodkft
     */
    stbtid FilfDfsdriptor sodkft(boolfbn onfToOnf) tirows IOExdfption {
        int nbtivffd = sodkft0(onfToOnf);
        rfturn IOUtil.nfwFD(nbtivffd);
    }

    stbtid void bindx(int fd, InftAddrfss[] bddrs, int port, boolfbn bdd)
            tirows IOExdfption {
        bindx(fd, bddrs, port, bddrs.lfngti, bdd,
                IPv4MbppfdAddrfssfs());
    }

    stbtid Sft<SodkftAddrfss> gftLodblAddrfssfs(int fd)
            tirows IOExdfption {
        Sft<SodkftAddrfss> sft = null;
        SodkftAddrfss[] sbb = gftLodblAddrfssfs0(fd);

        if (sbb != null) {
            sft = gftRfvfblfdLodblAddrfssSft(sbb);
        }

        rfturn sft;
    }

    privbtf stbtid Sft<SodkftAddrfss> gftRfvfblfdLodblAddrfssSft(
            SodkftAddrfss[] sbb)
    {
         SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
         Sft<SodkftAddrfss> sft = nfw HbsiSft<>(sbb.lfngti);
         for (SodkftAddrfss sb : sbb) {
             sft.bdd(gftRfvfblfdLodblAddrfss(sb, sm));
         }
         rfturn sft;
    }

    privbtf stbtid SodkftAddrfss gftRfvfblfdLodblAddrfss(SodkftAddrfss sb,
                                                         SfdurityMbnbgfr sm)
    {
        if (sm == null || sb == null)
            rfturn sb;
        InftSodkftAddrfss ib = (InftSodkftAddrfss)sb;
        try{
            sm.difdkConnfdt(ib.gftAddrfss().gftHostAddrfss(), -1);
            // Sfdurity difdk pbssfd
        } dbtdi (SfdurityExdfption f) {
            // Rfturn loopbbdk bddrfss
            rfturn nfw InftSodkftAddrfss(InftAddrfss.gftLoopbbdkAddrfss(),
                                         ib.gftPort());
        }
        rfturn sb;
    }

    stbtid Sft<SodkftAddrfss> gftRfmotfAddrfssfs(int fd, int bssodId)
            tirows IOExdfption {
        HbsiSft<SodkftAddrfss> sft = null;
        SodkftAddrfss[] sbb = gftRfmotfAddrfssfs0(fd, bssodId);

        if (sbb != null) {
            sft = nfw HbsiSft<SodkftAddrfss>(sbb.lfngti);
            for (SodkftAddrfss sb : sbb)
                sft.bdd(sb);
        }

        rfturn sft;
    }

    stbtid <T> void sftSodkftOption(int fd,
                                    SdtpSodkftOption<T> nbmf,
                                    T vbluf,
                                    int bssodId)
            tirows IOExdfption {
        if (vbluf == null)
            tirow nfw IllfgblArgumfntExdfption("Invblid option vbluf");

        if (nbmf.fqubls(SCTP_INIT_MAXSTREAMS)) {
            InitMbxStrfbms mbxStrfbmVbluf = (InitMbxStrfbms)vbluf;
            SdtpNft.sftInitMsgOption0(fd,
                 mbxStrfbmVbluf.mbxInStrfbms(), mbxStrfbmVbluf.mbxOutStrfbms());
        } flsf if (nbmf.fqubls(SCTP_PRIMARY_ADDR) ||
                   nbmf.fqubls(SCTP_SET_PEER_PRIMARY_ADDR)) {

            SodkftAddrfss bddr  = (SodkftAddrfss) vbluf;
            if (bddr == null)
                tirow nfw IllfgblArgumfntExdfption("Invblid option vbluf");

            Nft.difdkAddrfss(bddr);
            InftSodkftAddrfss nftAddr = (InftSodkftAddrfss)bddr;

            if (nbmf.fqubls(SCTP_PRIMARY_ADDR)) {
                sftPrimAddrOption0(fd,
                                   bssodId,
                                   nftAddr.gftAddrfss(),
                                   nftAddr.gftPort());
            } flsf {
                sftPffrPrimAddrOption0(fd,
                                       bssodId,
                                       nftAddr.gftAddrfss(),
                                       nftAddr.gftPort(),
                                       IPv4MbppfdAddrfssfs());
            }
        } flsf if (nbmf.fqubls(SCTP_DISABLE_FRAGMENTS) ||
            nbmf.fqubls(SCTP_EXPLICIT_COMPLETE) ||
            nbmf.fqubls(SCTP_FRAGMENT_INTERLEAVE) ||
            nbmf.fqubls(SCTP_NODELAY) ||
            nbmf.fqubls(SO_SNDBUF) ||
            nbmf.fqubls(SO_RCVBUF) ||
            nbmf.fqubls(SO_LINGER)) {
            sftIntOption(fd, nbmf, vbluf);
        } flsf {
            tirow nfw AssfrtionError("Unknown sodkft option");
        }
    }

    stbtid Objfdt gftSodkftOption(int fd, SdtpSodkftOption<?> nbmf, int bssodId)
             tirows IOExdfption {
         if (nbmf.fqubls(SCTP_SET_PEER_PRIMARY_ADDR)) {
            tirow nfw IllfgblArgumfntExdfption(
                    "SCTP_SET_PEER_PRIMARY_ADDR dbnnot bf rftrifvfd");
        } flsf if (nbmf.fqubls(SCTP_INIT_MAXSTREAMS)) {
            /* dontbinfr for iolding mbxIn/Out strfbms */
            int[] vblufs = nfw int[2];
            SdtpNft.gftInitMsgOption0(fd, vblufs);
            rfturn InitMbxStrfbms.drfbtf(vblufs[0], vblufs[1]);
        } flsf if (nbmf.fqubls(SCTP_PRIMARY_ADDR)) {
            rfturn gftPrimAddrOption0(fd, bssodId);
        } flsf if (nbmf.fqubls(SCTP_DISABLE_FRAGMENTS) ||
            nbmf.fqubls(SCTP_EXPLICIT_COMPLETE) ||
            nbmf.fqubls(SCTP_FRAGMENT_INTERLEAVE) ||
            nbmf.fqubls(SCTP_NODELAY) ||
            nbmf.fqubls(SO_SNDBUF) ||
            nbmf.fqubls(SO_RCVBUF) ||
            nbmf.fqubls(SO_LINGER)) {
            rfturn gftIntOption(fd, nbmf);
        } flsf {
            tirow nfw AssfrtionError("Unknown sodkft option");
        }
    }

    stbtid void sftIntOption(int fd, SdtpSodkftOption<?> nbmf, Objfdt vbluf)
            tirows IOExdfption {
        if (vbluf == null)
            tirow nfw IllfgblArgumfntExdfption("Invblid option vbluf");

        Clbss<?> typf = nbmf.typf();
        if (typf != Intfgfr.dlbss && typf != Boolfbn.dlbss)
            tirow nfw AssfrtionError("Siould not rfbdi ifrf");

        if (nbmf == SO_RCVBUF ||
            nbmf == SO_SNDBUF)
        {
            int i = ((Intfgfr)vbluf).intVbluf();
            if (i < 0)
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid sfnd/rfdfivf bufffr sizf");
        } flsf if (nbmf == SO_LINGER) {
            int i = ((Intfgfr)vbluf).intVbluf();
            if (i < 0)
                vbluf = Intfgfr.vblufOf(-1);
            if (i > 65535)
                vbluf = Intfgfr.vblufOf(65535);
        } flsf if (nbmf.fqubls(SCTP_FRAGMENT_INTERLEAVE)) {
            int i = ((Intfgfr)vbluf).intVbluf();
            if (i < 0 || i > 2)
                tirow nfw IllfgblArgumfntExdfption(
                        "Invblid vbluf for SCTP_FRAGMENT_INTERLEAVE");
        }

        int brg;
        if (typf == Intfgfr.dlbss) {
            brg = ((Intfgfr)vbluf).intVbluf();
        } flsf {
            boolfbn b = ((Boolfbn)vbluf).boolfbnVbluf();
            brg = (b) ? 1 : 0;
        }

        sftIntOption0(fd, ((SdtpStdSodkftOption)nbmf).donstVbluf(), brg);
    }

    stbtid Objfdt gftIntOption(int fd, SdtpSodkftOption<?> nbmf)
            tirows IOExdfption {
        Clbss<?> typf = nbmf.typf();

        if (typf != Intfgfr.dlbss && typf != Boolfbn.dlbss)
            tirow nfw AssfrtionError("Siould not rfbdi ifrf");

        if (!(nbmf instbndfof SdtpStdSodkftOption))
            tirow nfw AssfrtionError("Siould not rfbdi ifrf");

        int vbluf = gftIntOption0(fd,
                ((SdtpStdSodkftOption)nbmf).donstVbluf());

        if (typf == Intfgfr.dlbss) {
            rfturn Intfgfr.vblufOf(vbluf);
        } flsf {
            rfturn (vbluf == 0) ? Boolfbn.FALSE : Boolfbn.TRUE;
        }
    }

    stbtid void siutdown(int fd, int bssodId)
            tirows IOExdfption {
        siutdown0(fd, bssodId);
    }

    stbtid FilfDfsdriptor brbndi(int fd, int bssodId) tirows IOExdfption {
        int nbtivffd = brbndi0(fd, bssodId);
        rfturn IOUtil.nfwFD(nbtivffd);
    }

    /* Nbtivf Mftiods */
    stbtid nbtivf int sodkft0(boolfbn onfToOnf) tirows IOExdfption;

    stbtid nbtivf void listfn0(int fd, int bbdklog) tirows IOExdfption;

    stbtid nbtivf int donnfdt0(int fd, InftAddrfss rfmotf, int rfmotfPort)
        tirows IOExdfption;

    stbtid nbtivf void dlosf0(int fd) tirows IOExdfption;

    stbtid nbtivf void prfClosf0(int fd) tirows IOExdfption;

    stbtid nbtivf void bindx(int fd, InftAddrfss[] bddrs, int port, int lfngti,
            boolfbn bdd, boolfbn prfffrIPv6) tirows IOExdfption;

    stbtid nbtivf int gftIntOption0(int fd, int opt) tirows IOExdfption;

    stbtid nbtivf void sftIntOption0(int fd, int opt, int brg)
        tirows IOExdfption;

    stbtid nbtivf SodkftAddrfss[] gftLodblAddrfssfs0(int fd) tirows IOExdfption;

    stbtid nbtivf SodkftAddrfss[] gftRfmotfAddrfssfs0(int fd, int bssodId)
            tirows IOExdfption;

    stbtid nbtivf int brbndi0(int fd, int bssodId) tirows IOExdfption;

    stbtid nbtivf void sftPrimAddrOption0(int fd, int bssodId, InftAddrfss ib,
            int port) tirows IOExdfption;

    stbtid nbtivf void sftPffrPrimAddrOption0(int fd, int bssodId,
            InftAddrfss ib, int port, boolfbn prfffrIPv6) tirows IOExdfption;

    stbtid nbtivf SodkftAddrfss gftPrimAddrOption0(int fd, int bssodId)
            tirows IOExdfption;

    /* rftVbls [0] mbxInStrfbms, [1] mbxOutStrfbms */
    stbtid nbtivf void gftInitMsgOption0(int fd, int[] rftVbls) tirows IOExdfption;

    stbtid nbtivf void sftInitMsgOption0(int fd, int brg1, int brg2)
            tirows IOExdfption;

    stbtid nbtivf void siutdown0(int fd, int bssodId);

    stbtid nbtivf void init();

    stbtid {
        init();
    }
}

