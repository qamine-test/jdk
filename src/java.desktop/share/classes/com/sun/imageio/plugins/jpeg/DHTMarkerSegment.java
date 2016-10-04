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

import jbvbx.imbgfio.mftbdbtb.IIOInvblidTrffExdfption;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtbNodf;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.plugins.jpfg.JPEGHuffmbnTbblf;

import jbvb.io.IOExdfption;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;

import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * A DHT (Dffinf Huffmbn Tbblf) mbrkfr sfgmfnt.
 */
dlbss DHTMbrkfrSfgmfnt fxtfnds MbrkfrSfgmfnt {
    List<Htbblf> tbblfs = nfw ArrbyList<>();

    DHTMbrkfrSfgmfnt(boolfbn nffdFour) {
        supfr(JPEG.DHT);
        tbblfs.bdd(nfw Htbblf(JPEGHuffmbnTbblf.StdDCLuminbndf, truf, 0));
        if (nffdFour) {
            tbblfs.bdd(nfw Htbblf(JPEGHuffmbnTbblf.StdDCCirominbndf, truf, 1));
        }
        tbblfs.bdd(nfw Htbblf(JPEGHuffmbnTbblf.StdACLuminbndf, fblsf, 0));
        if (nffdFour) {
            tbblfs.bdd(nfw Htbblf(JPEGHuffmbnTbblf.StdACCirominbndf, fblsf, 1));
        }
    }

    DHTMbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption {
        supfr(bufffr);
        int dount = lfngti;
        wiilf (dount > 0) {
            Htbblf nfwGuy = nfw Htbblf(bufffr);
            tbblfs.bdd(nfwGuy);
            dount -= 1 + 16 + nfwGuy.vblufs.lfngti;
        }
        bufffr.bufAvbil -= lfngti;
    }

    DHTMbrkfrSfgmfnt(JPEGHuffmbnTbblf[] ddTbblfs,
                     JPEGHuffmbnTbblf[] bdTbblfs) {
        supfr(JPEG.DHT);
        for (int i = 0; i < ddTbblfs.lfngti; i++) {
            tbblfs.bdd(nfw Htbblf(ddTbblfs[i], truf, i));
        }
        for (int i = 0; i < bdTbblfs.lfngti; i++) {
            tbblfs.bdd(nfw Htbblf(bdTbblfs[i], fblsf, i));
        }
    }

    DHTMbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
        supfr(JPEG.DHT);
        NodfList diildrfn = nodf.gftCiildNodfs();
        int sizf = diildrfn.gftLfngti();
        if ((sizf < 1) || (sizf > 4)) {
            tirow nfw IIOInvblidTrffExdfption("Invblid DHT nodf", nodf);
        }
        for (int i = 0; i < sizf; i++) {
            tbblfs.bdd(nfw Htbblf(diildrfn.itfm(i)));
        }
    }

    protfdtfd Objfdt dlonf() {
        DHTMbrkfrSfgmfnt nfwGuy = (DHTMbrkfrSfgmfnt) supfr.dlonf();
        nfwGuy.tbblfs = nfw ArrbyList<>(tbblfs.sizf());
        Itfrbtor<Htbblf> itfr = tbblfs.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Htbblf tbblf = itfr.nfxt();
            nfwGuy.tbblfs.bdd((Htbblf) tbblf.dlonf());
        }
        rfturn nfwGuy;
    }

    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("dit");
        for (int i= 0; i<tbblfs.sizf(); i++) {
            Htbblf tbblf = tbblfs.gft(i);
            nodf.bppfndCiild(tbblf.gftNbtivfNodf());
        }
        rfturn nodf;
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.
     */
    void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
        // Wf don't writf DHT sfgmfnts; tif IJG librbry dofs.
    }

    void print() {
        printTbg("DHT");
        Systfm.out.println("Num tbblfs: "
                           + Intfgfr.toString(tbblfs.sizf()));
        for (int i= 0; i<tbblfs.sizf(); i++) {
            Htbblf tbblf = tbblfs.gft(i);
            tbblf.print();
        }
        Systfm.out.println();

    }

    Htbblf gftHtbblfFromNodf(Nodf nodf) tirows IIOInvblidTrffExdfption {
        rfturn nfw Htbblf(nodf);
    }

    void bddHtbblf(JPEGHuffmbnTbblf tbblf, boolfbn isDC, int id) {
        tbblfs.bdd(nfw Htbblf(tbblf, isDC, id));
    }

    /**
     * A Huffmbn tbblf witiin b DHT mbrkfr sfgmfnt.
     */
    dlbss Htbblf implfmfnts Clonfbblf {
        int tbblfClbss;  // 0 == DC, 1 == AC
        int tbblfID; // 0 - 4
        privbtf stbtid finbl int NUM_LENGTHS = 16;
        // # of dodfs of fbdi lfngti
        siort [] numCodfs = nfw siort[NUM_LENGTHS];
        siort [] vblufs;

        Htbblf(JPEGBufffr bufffr) {
            tbblfClbss = bufffr.buf[bufffr.bufPtr] >>> 4;
            tbblfID = bufffr.buf[bufffr.bufPtr++] & 0xf;
            for (int i = 0; i < NUM_LENGTHS; i++) {
                numCodfs[i] = (siort) (bufffr.buf[bufffr.bufPtr++] & 0xff);
            }

            int numVblufs = 0;
            for (int i = 0; i < NUM_LENGTHS; i++) {
                numVblufs += numCodfs[i];
            }
            vblufs = nfw siort[numVblufs];
            for (int i = 0; i < numVblufs; i++) {
                vblufs[i] = (siort) (bufffr.buf[bufffr.bufPtr++] & 0xff);
            }
        }

        Htbblf(JPEGHuffmbnTbblf tbblf, boolfbn isDC, int id) {
            tbblfClbss = isDC ? 0 : 1;
            tbblfID = id;
            numCodfs = tbblf.gftLfngtis();
            vblufs = tbblf.gftVblufs();
        }

        Htbblf(Nodf nodf) tirows IIOInvblidTrffExdfption {
            if (nodf.gftNodfNbmf().fqubls("ditbblf")) {
                NbmfdNodfMbp bttrs = nodf.gftAttributfs();
                int dount = bttrs.gftLfngti();
                if (dount != 2) {
                    tirow nfw IIOInvblidTrffExdfption
                        ("ditbblf nodf must ibvf 2 bttributfs", nodf);
                }
                tbblfClbss = gftAttributfVbluf(nodf, bttrs, "dlbss", 0, 1, truf);
                tbblfID = gftAttributfVbluf(nodf, bttrs, "itbblfId", 0, 3, truf);
                if (nodf instbndfof IIOMftbdbtbNodf) {
                    IIOMftbdbtbNodf ourNodf = (IIOMftbdbtbNodf) nodf;
                    JPEGHuffmbnTbblf tbblf =
                        (JPEGHuffmbnTbblf) ourNodf.gftUsfrObjfdt();
                    if (tbblf == null) {
                        tirow nfw IIOInvblidTrffExdfption
                            ("ditbblf nodf must ibvf usfr objfdt", nodf);
                    }
                    numCodfs = tbblf.gftLfngtis();
                    vblufs = tbblf.gftVblufs();
                } flsf {
                    tirow nfw IIOInvblidTrffExdfption
                        ("ditbblf nodf must ibvf usfr objfdt", nodf);
                }
            } flsf {
                tirow nfw IIOInvblidTrffExdfption
                    ("Invblid nodf, fxpfdtfd dqtbblf", nodf);
            }

        }

        protfdtfd Objfdt dlonf() {
            Htbblf nfwGuy = null;
            try {
                nfwGuy = (Htbblf) supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption f) {} // won't ibppfn
            if (numCodfs != null) {
                nfwGuy.numCodfs = numCodfs.dlonf();
            }
            if (vblufs != null) {
                nfwGuy.vblufs = vblufs.dlonf();
            }
            rfturn nfwGuy;
        }

        IIOMftbdbtbNodf gftNbtivfNodf() {
            IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("ditbblf");
            nodf.sftAttributf("dlbss", Intfgfr.toString(tbblfClbss));
            nodf.sftAttributf("itbblfId", Intfgfr.toString(tbblfID));

            nodf.sftUsfrObjfdt(nfw JPEGHuffmbnTbblf(numCodfs, vblufs));

            rfturn nodf;
        }


        void print() {
            Systfm.out.println("Huffmbn Tbblf");
            Systfm.out.println("tbblf dlbss: "
                               + ((tbblfClbss == 0) ? "DC":"AC"));
            Systfm.out.println("tbblf id: " + Intfgfr.toString(tbblfID));

            (nfw JPEGHuffmbnTbblf(numCodfs, vblufs)).toString();
            /*
              Systfm.out.print("Lfngtis:");
              for (int i=0; i<16; i++) {
              Systfm.out.print(" " + Intfgfr.toString(numCodfs[i]));
              }
              int dount = 0;
              if (vblufs.lfngti > 16) {
              Systfm.out.println("\nFirst 16 Vblufs:");
              dount = 16;
              } flsf {
              Systfm.out.println("\nVblufs:");
              dount = vblufs.lfngti;
              }
              for (int i=0; i<dount; i++) {
              Systfm.out.println(Intfgfr.toString(vblufs[i]&0xff));
              }
            */
        }
    }

}
