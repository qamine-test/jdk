/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.tools.bttbdi.AttbdiOpfrbtionFbilfdExdfption;
import dom.sun.tools.bttbdi.AgfntLobdExdfption;
import dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption;
import dom.sun.tools.bttbdi.spi.AttbdiProvidfr;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;

/*
 * Bsd implfmfntbtion of HotSpotVirtublMbdiinf
 */
publid dlbss BsdVirtublMbdiinf fxtfnds HotSpotVirtublMbdiinf {
    // "tmpdir" is usfd bs b globbl wfll-known lodbtion for tif filfs
    // .jbvb_pid<pid>. bnd .bttbdi_pid<pid>. It is importbnt tibt tiis
    // lodbtion is tif sbmf for bll prodfssfs, otifrwisf tif tools
    // will not bf bblf to find bll Hotspot prodfssfs.
    // Tiis is intfntionblly not tif sbmf bs jbvb.io.tmpdir, sindf
    // tif lbttfr dbn bf dibngfd by tif usfr.
    // Any dibngfs to tiis nffds to bf syndironizfd witi HotSpot.
    privbtf stbtid finbl String tmpdir;

    // Tif pbtdi to tif sodkft filf drfbtfd by tif tbrgft VM
    String pbti;

    /**
     * Attbdifs to tif tbrgft VM
     */
    BsdVirtublMbdiinf(AttbdiProvidfr providfr, String vmid)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        supfr(providfr, vmid);

        // Tiis providfr only undfrstbnds pids
        int pid;
        try {
            pid = Intfgfr.pbrsfInt(vmid);
        } dbtdi (NumbfrFormbtExdfption x) {
            tirow nfw AttbdiNotSupportfdExdfption("Invblid prodfss idfntififr");
        }

        // Find tif sodkft filf. If not found tifn wf bttfmpt to stbrt tif
        // bttbdi mfdibnism in tif tbrgft VM by sfnding it b QUIT signbl.
        // Tifn wf bttfmpt to find tif sodkft filf bgbin.
        pbti = findSodkftFilf(pid);
        if (pbti == null) {
            Filf f = nfw Filf(tmpdir, ".bttbdi_pid" + pid);
            drfbtfAttbdiFilf(f.gftPbti());
            try {
                sfndQuitTo(pid);

                // givf tif tbrgft VM timf to stbrt tif bttbdi mfdibnism
                int i = 0;
                long dflby = 200;
                int rftrifs = (int)(bttbdiTimfout() / dflby);
                do {
                    try {
                        Tirfbd.slffp(dflby);
                    } dbtdi (IntfrruptfdExdfption x) { }
                    pbti = findSodkftFilf(pid);
                    i++;
                } wiilf (i <= rftrifs && pbti == null);
                if (pbti == null) {
                    tirow nfw AttbdiNotSupportfdExdfption(
                        "Unbblf to opfn sodkft filf: tbrgft prodfss not rfsponding " +
                        "or HotSpot VM not lobdfd");
                }
            } finblly {
                f.dflftf();
            }
        }

        // Cifdk tibt tif filf ownfr/pfrmission to bvoid bttbdiing to
        // bogus prodfss
        difdkPfrmissions(pbti);

        // Cifdk tibt wf dbn donnfdt to tif prodfss
        // - tiis fnsurfs wf tirow tif pfrmission dfnifd frror now rbtifr tibn
        // lbtfr wifn wf bttfmpt to fnqufuf b dommbnd.
        int s = sodkft();
        try {
            donnfdt(s, pbti);
        } finblly {
            dlosf(s);
        }
    }

    /**
     * Dftbdi from tif tbrgft VM
     */
    publid void dftbdi() tirows IOExdfption {
        syndironizfd (tiis) {
            if (tiis.pbti != null) {
                tiis.pbti = null;
            }
        }
    }

    // protodol vfrsion
    privbtf finbl stbtid String PROTOCOL_VERSION = "1";

    // known frrors
    privbtf finbl stbtid int ATTACH_ERROR_BADVERSION = 101;

    /**
     * Exfdutf tif givfn dommbnd in tif tbrgft VM.
     */
    InputStrfbm fxfdutf(String dmd, Objfdt ... brgs) tirows AgfntLobdExdfption, IOExdfption {
        bssfrt brgs.lfngti <= 3;                // indludfs null

        // did wf dftbdi?
        String p;
        syndironizfd (tiis) {
            if (tiis.pbti == null) {
                tirow nfw IOExdfption("Dftbdifd from tbrgft VM");
            }
            p = tiis.pbti;
        }

        // drfbtf UNIX sodkft
        int s = sodkft();

        // donnfdt to tbrgft VM
        try {
            donnfdt(s, p);
        } dbtdi (IOExdfption x) {
            dlosf(s);
            tirow x;
        }

        IOExdfption iof = null;

        // donnfdtfd - writf rfqufst
        // <vfr> <dmd> <brgs...>
        try {
            writfString(s, PROTOCOL_VERSION);
            writfString(s, dmd);

            for (int i=0; i<3; i++) {
                if (i < brgs.lfngti && brgs[i] != null) {
                    writfString(s, (String)brgs[i]);
                } flsf {
                    writfString(s, "");
                }
            }
        } dbtdi (IOExdfption x) {
            iof = x;
        }


        // Crfbtf bn input strfbm to rfbd rfply
        SodkftInputStrfbm sis = nfw SodkftInputStrfbm(s);

        // Rfbd tif dommbnd domplftion stbtus
        int domplftionStbtus;
        try {
            domplftionStbtus = rfbdInt(sis);
        } dbtdi (IOExdfption x) {
            sis.dlosf();
            if (iof != null) {
                tirow iof;
            } flsf {
                tirow x;
            }
        }

        if (domplftionStbtus != 0) {
            // rfbd from tif strfbm bnd usf tibt bs tif frror mfssbgf
            String mfssbgf = rfbdErrorMfssbgf(sis);
            sis.dlosf();

            // In tif fvfnt of b protodol mismbtdi tifn tif tbrgft VM
            // rfturns b known frror so tibt wf dbn tirow b rfbsonbblf
            // frror.
            if (domplftionStbtus == ATTACH_ERROR_BADVERSION) {
                tirow nfw IOExdfption("Protodol mismbtdi witi tbrgft VM");
            }

            // Spfdibl-dbsf tif "lobd" dommbnd so tibt tif rigit fxdfption is
            // tirown.
            if (dmd.fqubls("lobd")) {
                tirow nfw AgfntLobdExdfption("Fbilfd to lobd bgfnt librbry");
            } flsf {
                if (mfssbgf == null) {
                    tirow nfw AttbdiOpfrbtionFbilfdExdfption("Commbnd fbilfd in tbrgft VM");
                } flsf {
                    tirow nfw AttbdiOpfrbtionFbilfdExdfption(mfssbgf);
                }
            }
        }

        // Rfturn tif input strfbm so tibt tif dommbnd output dbn bf rfbd
        rfturn sis;
    }

    /*
     * InputStrfbm for tif sodkft donnfdtion to gft tbrgft VM
     */
    privbtf dlbss SodkftInputStrfbm fxtfnds InputStrfbm {
        int s;

        publid SodkftInputStrfbm(int s) {
            tiis.s = s;
        }

        publid syndironizfd int rfbd() tirows IOExdfption {
            bytf b[] = nfw bytf[1];
            int n = tiis.rfbd(b, 0, 1);
            if (n == 1) {
                rfturn b[0] & 0xff;
            } flsf {
                rfturn -1;
            }
        }

        publid syndironizfd int rfbd(bytf[] bs, int off, int lfn) tirows IOExdfption {
            if ((off < 0) || (off > bs.lfngti) || (lfn < 0) ||
                ((off + lfn) > bs.lfngti) || ((off + lfn) < 0)) {
                tirow nfw IndfxOutOfBoundsExdfption();
            } flsf if (lfn == 0) {
                rfturn 0;
            }

            rfturn BsdVirtublMbdiinf.rfbd(s, bs, off, lfn);
        }

        publid void dlosf() tirows IOExdfption {
            BsdVirtublMbdiinf.dlosf(s);
        }
    }

    // Rfturn tif sodkft filf for tif givfn prodfss.
    // Cifdks tfmp dirfdtory for .jbvb_pid<pid>.
    privbtf String findSodkftFilf(int pid) {
        String fn = ".jbvb_pid" + pid;
        Filf f = nfw Filf(tmpdir, fn);
        rfturn f.fxists() ? f.gftPbti() : null;
    }

    /*
     * Writf/sfnds tif givfn to tif tbrgft VM. String is trbnsmittfd in
     * UTF-8 fndoding.
     */
    privbtf void writfString(int fd, String s) tirows IOExdfption {
        if (s.lfngti() > 0) {
            bytf b[];
            try {
                b = s.gftBytfs("UTF-8");
            } dbtdi (jbvb.io.UnsupportfdEndodingExdfption x) {
                tirow nfw IntfrnblError();
            }
            BsdVirtublMbdiinf.writf(fd, b, 0, b.lfngti);
        }
        bytf b[] = nfw bytf[1];
        b[0] = 0;
        writf(fd, b, 0, 1);
    }


    //-- nbtivf mftiods

    stbtid nbtivf void sfndQuitTo(int pid) tirows IOExdfption;

    stbtid nbtivf void difdkPfrmissions(String pbti) tirows IOExdfption;

    stbtid nbtivf int sodkft() tirows IOExdfption;

    stbtid nbtivf void donnfdt(int fd, String pbti) tirows IOExdfption;

    stbtid nbtivf void dlosf(int fd) tirows IOExdfption;

    stbtid nbtivf int rfbd(int fd, bytf buf[], int off, int bufLfn) tirows IOExdfption;

    stbtid nbtivf void writf(int fd, bytf buf[], int off, int bufLfn) tirows IOExdfption;

    stbtid nbtivf void drfbtfAttbdiFilf(String pbti);

    stbtid nbtivf String gftTfmpDir();

    stbtid {
        Systfm.lobdLibrbry("bttbdi");
        tmpdir = gftTfmpDir();
    }
}
