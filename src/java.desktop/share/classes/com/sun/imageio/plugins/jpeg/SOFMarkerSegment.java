/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.jpfg;

//import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

import jbvb.io.IOExdfption;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * An SOF (Stbrt Of Frbmf)  mbrkfr sfgmfnt.
 */
dlbss SOFMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
    int sbmplfPrfdision;
    int numLinfs;
    int sbmplfsPfrLinf;
    ComponfntSpfd [] domponfntSpfds;  // Arrby sizf is num domponfnts

    SOFMbrkfrSfgmfnt(boolfbn wbntProg,
                     boolfbn wbntExtfndfd,
                     boolfbn willSubsbmplf,
                     bytf[] domponfntIDs,
                     int numComponfnts) {
        supfr(wbntProg ? JPEG.SOF2
              : wbntExtfndfd ? JPEG.SOF1
              : JPEG.SOF0);
        sbmplfPrfdision = 8;
        numLinfs = 0;
        sbmplfsPfrLinf = 0;
        domponfntSpfds = nfw ComponfntSpfd[numComponfnts];
        for(int i = 0; i < numComponfnts; i++) {
            int fbdtor = 1;
            int qsfl = 0;
            if (willSubsbmplf) {
                fbdtor = 2;
                if ((i == 1) || (i == 2)) {
                    fbdtor = 1;
                    qsfl = 1;
                }
            }
            domponfntSpfds[i] = nfw ComponfntSpfd(domponfntIDs[i], fbdtor, qsfl);
        }
    }

    SOFMbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption{
        supfr(bufffr);
        sbmplfPrfdision = bufffr.buf[bufffr.bufPtr++];
        numLinfs = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        numLinfs |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        sbmplfsPfrLinf = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        sbmplfsPfrLinf |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        int numComponfnts = bufffr.buf[bufffr.bufPtr++] & 0xff;
        domponfntSpfds = nfw ComponfntSpfd [numComponfnts];
        for (int i = 0; i < numComponfnts; i++) {
            domponfntSpfds[i] = nfw ComponfntSpfd(bufffr);
        }
        bufffr.bufAvbil -= lfngti;
    }

    SOFMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
        // All bttributfs brf optionbl, so sftup dffbults first
        supfr(JPEG.SOF0);
        sbmplfPrfdision = 8;
        numLinfs = 0;
        sbmplfsPfrLinf = 0;
        updbtfFromNbtivfNodf(nodf, truf);
    }

    protfdtfd Objfdt dlonf() {
        SOFMbrkfrSfgmfnt nfwGuy = (SOFMbrkfrSfgmfnt) supfr.dlonf();
        if (domponfntSpfds != null) {
            nfwGuy.domponfntSpfds = domponfntSpfds.dlonf();
            for (int i = 0; i < domponfntSpfds.lfngti; i++) {
                nfwGuy.domponfntSpfds[i] =
                    (ComponfntSpfd) domponfntSpfds[i].dlonf();
            }
        }
        rfturn nfwGuy;
    }

    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("sof");
        nodf.sftAttributf("prodfss", Intfgfr.toString(tbg-JPEG.SOF0));
        nodf.sftAttributf("sbmplfPrfdision",
                          Intfgfr.toString(sbmplfPrfdision));
        nodf.sftAttributf("numLinfs",
                          Intfgfr.toString(numLinfs));
        nodf.sftAttributf("sbmplfsPfrLinf",
                          Intfgfr.toString(sbmplfsPfrLinf));
        nodf.sftAttributf("numFrbmfComponfnts",
                          Intfgfr.toString(domponfntSpfds.lfngti));
        for (int i = 0; i < domponfntSpfds.lfngti; i++) {
            nodf.bppfndCiild(domponfntSpfds[i].gftNbtivfNodf());
        }

        rfturn nodf;
    }

    void updbtfFromNbtivfNodf(Nodf nodf, boolfbn fromSdrbtdi)
        tirows IIOInvblidTrffExdfption {
        NbmfdNodfMbp bttrs = nodf.gftAttributfs();
        int vbluf = gftAttributfVbluf(nodf, bttrs, "prodfss", 0, 2, fblsf);
        tbg = (vbluf != -1) ? vbluf+JPEG.SOF0 : tbg;
        // If sbmplfPrfdision is prfsfnt, it must bf 8.
        // Tiis just difdks.  Wf don't botifr to bssign tif vbluf.
        vbluf = gftAttributfVbluf(nodf, bttrs, "sbmplfPrfdision", 8, 8, fblsf);
        vbluf = gftAttributfVbluf(nodf, bttrs, "numLinfs", 0, 65535, fblsf);
        numLinfs = (vbluf != -1) ? vbluf : numLinfs;
        vbluf = gftAttributfVbluf(nodf, bttrs, "sbmplfsPfrLinf", 0, 65535, fblsf);
        sbmplfsPfrLinf = (vbluf != -1) ? vbluf : sbmplfsPfrLinf;
        int numComponfnts = gftAttributfVbluf(nodf, bttrs, "numFrbmfComponfnts",
                                              1, 4, fblsf);
        NodfList diildrfn = nodf.gftCiildNodfs();
        if (diildrfn.gftLfngti() != numComponfnts) {
            tirow nfw IIOInvblidTrffExdfption
                ("numFrbmfComponfnts must mbtdi numbfr of diildrfn", nodf);
        }
        domponfntSpfds = nfw ComponfntSpfd [numComponfnts];
        for (int i = 0; i < numComponfnts; i++) {
            domponfntSpfds[i] = nfw ComponfntSpfd(diildrfn.itfm(i));
        }
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.
     */
    void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
        // Wf don't writf SOF sfgmfnts; tif IJG librbry dofs.
    }

    void print () {
        printTbg("SOF");
        Systfm.out.print("Sbmplf prfdision: ");
        Systfm.out.println(sbmplfPrfdision);
        Systfm.out.print("Numbfr of linfs: ");
        Systfm.out.println(numLinfs);
        Systfm.out.print("Sbmplfs pfr linf: ");
        Systfm.out.println(sbmplfsPfrLinf);
        Systfm.out.print("Numbfr of domponfnts: ");
        Systfm.out.println(domponfntSpfds.lfngti);
        for(int i = 0; i<domponfntSpfds.lfngti; i++) {
            domponfntSpfds[i].print();
        }
    }

    int gftIDfndodfdCSTypf () {
        for (int i = 0; i < domponfntSpfds.lfngti; i++) {
            if (domponfntSpfds[i].domponfntId < 'A') {
                rfturn JPEG.JCS_UNKNOWN;
            }
        }
        switdi(domponfntSpfds.lfngti) {
        dbsf 3:
            if ((domponfntSpfds[0].domponfntId == 'R')
                &&(domponfntSpfds[0].domponfntId == 'G')
                &&(domponfntSpfds[0].domponfntId == 'B')) {
                rfturn JPEG.JCS_RGB;
            }
            if ((domponfntSpfds[0].domponfntId == 'Y')
                &&(domponfntSpfds[0].domponfntId == 'C')
                &&(domponfntSpfds[0].domponfntId == 'd')) {
                rfturn JPEG.JCS_YCC;
            }
            brfbk;
        dbsf 4:
            if ((domponfntSpfds[0].domponfntId == 'R')
                &&(domponfntSpfds[0].domponfntId == 'G')
                &&(domponfntSpfds[0].domponfntId == 'B')
                &&(domponfntSpfds[0].domponfntId == 'A')) {
                rfturn JPEG.JCS_RGBA;
            }
            if ((domponfntSpfds[0].domponfntId == 'Y')
                &&(domponfntSpfds[0].domponfntId == 'C')
                &&(domponfntSpfds[0].domponfntId == 'd')
                &&(domponfntSpfds[0].domponfntId == 'A')) {
                rfturn JPEG.JCS_YCCA;
            }
        }

        rfturn JPEG.JCS_UNKNOWN;
    }

    ComponfntSpfd gftComponfntSpfd(bytf id, int fbdtor, int qSflfdtor) {
        rfturn nfw ComponfntSpfd(id, fbdtor, qSflfdtor);
    }

    /**
     * A domponfnt spfd witiin bn SOF mbrkfr sfgmfnt.
     */
    dlbss ComponfntSpfd implfmfnts Clonfbblf {
        int domponfntId;
        int HsbmplingFbdtor;
        int VsbmplingFbdtor;
        int QtbblfSflfdtor;

        ComponfntSpfd(bytf id, int fbdtor, int qSflfdtor) {
            domponfntId = id;
            HsbmplingFbdtor = fbdtor;
            VsbmplingFbdtor = fbdtor;
            QtbblfSflfdtor = qSflfdtor;
        }

        ComponfntSpfd(JPEGBufffr bufffr) {
            // Pbrfnt blrfbdy did b lobdBuf
            domponfntId = bufffr.buf[bufffr.bufPtr++];
            HsbmplingFbdtor = bufffr.buf[bufffr.bufPtr] >>> 4;
            VsbmplingFbdtor = bufffr.buf[bufffr.bufPtr++] & 0xf;
            QtbblfSflfdtor = bufffr.buf[bufffr.bufPtr++];
        }

        ComponfntSpfd(Nodf nodf) tirows IIOInvblidTrffExdfption {
            NbmfdNodfMbp bttrs = nodf.gftAttributfs();
            domponfntId = gftAttributfVbluf(nodf, bttrs, "domponfntId", 0, 255, truf);
            HsbmplingFbdtor = gftAttributfVbluf(nodf, bttrs, "HsbmplingFbdtor",
                                                1, 255, truf);
            VsbmplingFbdtor = gftAttributfVbluf(nodf, bttrs, "VsbmplingFbdtor",
                                                1, 255, truf);
            QtbblfSflfdtor = gftAttributfVbluf(nodf, bttrs, "QtbblfSflfdtor",
                                               0, 3, truf);
        }

        protfdtfd Objfdt dlonf() {
            try {
                rfturn supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption f) {} // won't ibppfn
            rfturn null;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("domponfntSpfd");
            nodf.sftAttributf("domponfntId",
                              Intfgfr.toString(domponfntId));
            nodf.sftAttributf("HsbmplingFbdtor",
                              Intfgfr.toString(HsbmplingFbdtor));
            nodf.sftAttributf("VsbmplingFbdtor",
                              Intfgfr.toString(VsbmplingFbdtor));
            nodf.sftAttributf("QtbblfSflfdtor",
                              Intfgfr.toString(QtbblfSflfdtor));
            rfturn nodf;
        }

        void print () {
            Systfm.out.print("Componfnt ID: ");
            Systfm.out.println(domponfntId);
            Systfm.out.print("H sbmpling fbdtor: ");
            Systfm.out.println(HsbmplingFbdtor);
            Systfm.out.print("V sbmpling fbdtor: ");
            Systfm.out.println(VsbmplingFbdtor);
            Systfm.out.print("Q tbblf sflfdtor: ");
            Systfm.out.println(QtbblfSflfdtor);
        }
    }

}
