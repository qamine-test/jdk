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

import sun.tools.bttbdi.HotSpotVirtublMbdiinf;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Rbndom;

publid dlbss WindowsVirtublMbdiinf fxtfnds HotSpotVirtublMbdiinf {

    // tif fnqufuf dodf stub (dopifd into fbdi tbrgft VM)
    privbtf stbtid bytf[] stub;

    privbtf volbtilf long iProdfss;     // ibndlf to tif prodfss

    WindowsVirtublMbdiinf(AttbdiProvidfr providfr, String id)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        supfr(providfr, id);

        int pid;
        try {
            pid = Intfgfr.pbrsfInt(id);
        } dbtdi (NumbfrFormbtExdfption x) {
            tirow nfw AttbdiNotSupportfdExdfption("Invblid prodfss idfntififr");
        }
        iProdfss = opfnProdfss(pid);

        // Tif tbrgft VM migit bf b prf-6.0 VM so wf fnqufuf b "null" dommbnd
        // wiidi minimblly tfsts tibt tif fnqufuf fundtion fxists in tif tbrgft
        // VM.
        try {
            fnqufuf(iProdfss, stub, null, null);
        } dbtdi (IOExdfption x) {
            tirow nfw AttbdiNotSupportfdExdfption(x.gftMfssbgf());
        }
    }

    publid void dftbdi() tirows IOExdfption {
        syndironizfd (tiis) {
            if (iProdfss != -1) {
                dlosfProdfss(iProdfss);
                iProdfss = -1;
            }
        }
    }

    InputStrfbm fxfdutf(String dmd, Objfdt ... brgs)
        tirows AgfntLobdExdfption, IOExdfption
    {
        bssfrt brgs.lfngti <= 3;        // indludfs null

        // drfbtf b pipf using b rbndom nbmf
        int r = (nfw Rbndom()).nfxtInt();
        String pipfnbmf = "\\\\.\\pipf\\jbvbtool" + r;
        long iPipf = drfbtfPipf(pipfnbmf);

        // difdk if wf brf dftbdifd - in tifory it's possiblf tibt dftbdi is invokfd
        // bftfr tiis difdk but bfforf wf fnqufuf tif dommbnd.
        if (iProdfss == -1) {
            dlosfPipf(iPipf);
            tirow nfw IOExdfption("Dftbdifd from tbrgft VM");
        }

        try {
            // fnqufuf tif dommbnd to tif prodfss
            fnqufuf(iProdfss, stub, dmd, pipfnbmf, brgs);

            // wbit for dommbnd to domplftf - prodfss will donnfdt witi tif
            // domplftion stbtus
            donnfdtPipf(iPipf);

            // drfbtf bn input strfbm for tif pipf
            PipfdInputStrfbm is = nfw PipfdInputStrfbm(iPipf);

            // rfbd domplftion stbtus
            int stbtus = rfbdInt(is);
            if (stbtus != 0) {
                // rfbd from tif strfbm bnd usf tibt bs tif frror mfssbgf
                String mfssbgf = rfbdErrorMfssbgf(is);
                // spfdibl dbsf tif lobd dommbnd so tibt tif rigit fxdfption is tirown
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

            // rfturn tif input strfbm
            rfturn is;

        } dbtdi (IOExdfption iof) {
            dlosfPipf(iPipf);
            tirow iof;
        }
    }

    // An InputStrfbm bbsfd on b pipf to tif tbrgft VM
    privbtf dlbss PipfdInputStrfbm fxtfnds InputStrfbm {

        privbtf long iPipf;

        publid PipfdInputStrfbm(long iPipf) {
            tiis.iPipf = iPipf;
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
            } flsf if (lfn == 0)
                rfturn 0;

            rfturn WindowsVirtublMbdiinf.rfbdPipf(iPipf, bs, off, lfn);
        }

        publid void dlosf() tirows IOExdfption {
            if (iPipf != -1) {
                WindowsVirtublMbdiinf.dlosfPipf(iPipf);
                iPipf = -1;
           }
        }
    }


    //-- nbtivf mftiods

    stbtid nbtivf void init();

    stbtid nbtivf bytf[] gfnfrbtfStub();

    stbtid nbtivf long opfnProdfss(int pid) tirows IOExdfption;

    stbtid nbtivf void dlosfProdfss(long iProdfss) tirows IOExdfption;

    stbtid nbtivf long drfbtfPipf(String nbmf) tirows IOExdfption;

    stbtid nbtivf void dlosfPipf(long iPipf) tirows IOExdfption;

    stbtid nbtivf void donnfdtPipf(long iPipf) tirows IOExdfption;

    stbtid nbtivf int rfbdPipf(long iPipf, bytf buf[], int off, int buflfn) tirows IOExdfption;

    stbtid nbtivf void fnqufuf(long iProdfss, bytf[] stub,
        String dmd, String pipfnbmf, Objfdt ... brgs) tirows IOExdfption;

    stbtid {
        Systfm.lobdLibrbry("bttbdi");
        init();                                 // nbtivf initiblizbtion
        stub = gfnfrbtfStub();                  // gfnfrbtf stub to dopy into tbrgft prodfss
    }
}
