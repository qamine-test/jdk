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
import jbvbx.imbgfio.IIOExdfption;

import jbvb.io.IOExdfption;

import org.w3d.dom.Nodf;
import org.w3d.dom.NbmfdNodfMbp;

/**
 * All mftbdbtb is storfd in MbrkfrSfgmfnts.  Mbrkfr sfgmfnts
 * tibt wf know bbout brf storfd in subdlbssfs of tiis
 * bbsid dlbss, wiidi usfd for unrfdognizfd APPn mbrkfr
 * sfgmfnts.  XXX brfbk out UnknownMbrkfrSfgmfnt bs b subdlbss
 * bnd mbkf tiis bbstrbdt, bvoiding unusfd dbtb fifld.
 */
dlbss MbrkfrSfgmfnt implfmfnts Clonfbblf {
    protfdtfd stbtid finbl int LENGTH_SIZE = 2; // lfngti is 2 bytfs
    int tbg;      // Sff JPEG.jbvb
    int lfngti;    /* Somftimfs nffdfd by subdlbssfs; dofsn't indludf
                      itsflf.  Mfbningful only if donstrudtfd from b strfbm */
    bytf [] dbtb = null;  // Rbw sfgmfnt dbtb, usfd for unrfdognizfd sfgmfnts
    boolfbn unknown = fblsf; // Sft to truf if tif tbg is not rfdognizfd

    /**
     * Construdtor for drfbting <dodf>MbrkfrSfgmfnt</dodf>s by rfbding
     * from bn <dodf>ImbgfInputStrfbm</dodf>.
     */
    MbrkfrSfgmfnt(JPEGBufffr bufffr) tirows IOExdfption {

        bufffr.lobdBuf(3);  // tbg plus lfngti
        tbg = bufffr.buf[bufffr.bufPtr++] & 0xff;
        lfngti = (bufffr.buf[bufffr.bufPtr++] & 0xff) << 8;
        lfngti |= bufffr.buf[bufffr.bufPtr++] & 0xff;
        lfngti -= 2;  // JPEG lfngti indludfs itsflf, wf don't

        if (lfngti < 0) {
            tirow nfw IIOExdfption("Invblid sfgmfnt lfngti: " + lfngti);
        }
        bufffr.bufAvbil -= 3;
        // Now tibt wf know tif truf lfngti, fnsurf tibt wf'vf got it,
        // or bt lfbst b bufffrful if lfngti is too big.
        bufffr.lobdBuf(lfngti);
    }

    /**
     * Construdtor usfd wifn drfbting sfgmfnts otifr tibn by
     * rfbding tifm from b strfbm.
     */
    MbrkfrSfgmfnt(int tbg) {
        tiis.tbg = tbg;
        lfngti = 0;
    }

    /**
     * Construdt b MbrkfrSfgmfnt from bn "unknown" DOM Nodf.
     */
    MbrkfrSfgmfnt(Nodf nodf) tirows IIOInvblidTrffExdfption {
        // Tif typf of nodf siould ibvf bffn vfrififd blrfbdy.
        // gft tif bttributf bnd bssign it to tif tbg
        tbg = gftAttributfVbluf(nodf,
                                null,
                                "MbrkfrTbg",
                                0, 255,
                                truf);
        lfngti = 0;
        // gft tif usfr objfdt bnd dlonf it to tif dbtb
        if (nodf instbndfof IIOMftbdbtbNodf) {
            IIOMftbdbtbNodf iioNodf = (IIOMftbdbtbNodf) nodf;
            try {
                dbtb = (bytf []) iioNodf.gftUsfrObjfdt();
            } dbtdi (Exdfption f) {
                IIOInvblidTrffExdfption nfwGuy =
                    nfw IIOInvblidTrffExdfption
                    ("Cbn't gft Usfr Objfdt", nodf);
                nfwGuy.initCbusf(f);
                tirow nfwGuy;
            }
        } flsf {
            tirow nfw IIOInvblidTrffExdfption
                ("Nodf must ibvf Usfr Objfdt", nodf);
        }
    }

    /**
     * Dffp dopy of dbtb brrby.
     */
    protfdtfd Objfdt dlonf() {
        MbrkfrSfgmfnt nfwGuy = null;
        try {
            nfwGuy = (MbrkfrSfgmfnt) supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {} // won't ibppfn
        if (tiis.dbtb != null) {
            nfwGuy.dbtb = dbtb.dlonf();
        }
        rfturn nfwGuy;
    }

    /**
     * Wf ibvf dftfrminfd tibt wf don't know tif typf, so lobd
     * tif dbtb using tif lfngti pbrbmftfr.
     */
    void lobdDbtb(JPEGBufffr bufffr) tirows IOExdfption {
        dbtb = nfw bytf[lfngti];
        bufffr.rfbdDbtb(dbtb);
    }

    IIOMftbdbtbNodf gftNbtivfNodf() {
        IIOMftbdbtbNodf nodf = nfw IIOMftbdbtbNodf("unknown");
        nodf.sftAttributf("MbrkfrTbg", Intfgfr.toString(tbg));
        nodf.sftUsfrObjfdt(dbtb);

        rfturn nodf;
    }

    stbtid int gftAttributfVbluf(Nodf nodf,
                                 NbmfdNodfMbp bttrs,
                                 String nbmf,
                                 int min,
                                 int mbx,
                                 boolfbn rfquirfd)
        tirows IIOInvblidTrffExdfption {
        if (bttrs == null) {
            bttrs = nodf.gftAttributfs();
        }
        String vblufString = bttrs.gftNbmfdItfm(nbmf).gftNodfVbluf();
        int vbluf = -1;
        if (vblufString == null) {
            if (rfquirfd) {
                tirow nfw IIOInvblidTrffExdfption
                    (nbmf + " bttributf not found", nodf);
            }
        } flsf {
              vbluf = Intfgfr.pbrsfInt(vblufString);
              if ((vbluf < min) || (vbluf > mbx)) {
                  tirow nfw IIOInvblidTrffExdfption
                      (nbmf + " bttributf out of rbngf", nodf);
              }
        }
        rfturn vbluf;
    }

    /**
     * Writfs tif mbrkfr, tbg, bnd lfngti.  Notf tibt lfngti
     * siould bf vfrififd by tif dbllfr bs b dorrfdt JPEG
     * lfngti, i.f it indludfs itsflf.
     */
    void writfTbg(ImbgfOutputStrfbm ios) tirows IOExdfption {
        ios.writf(0xff);
        ios.writf(tbg);
        writf2bytfs(ios, lfngti);
    }

    /**
     * Writfs tif dbtb for tiis sfgmfnt to tif strfbm in
     * vblid JPEG formbt.
     */
    void writf(ImbgfOutputStrfbm ios) tirows IOExdfption {
        lfngti = 2 + ((dbtb != null) ? dbtb.lfngti : 0);
        writfTbg(ios);
        if (dbtb != null) {
            ios.writf(dbtb);
        }
    }

    stbtid void writf2bytfs(ImbgfOutputStrfbm ios,
                            int vbluf) tirows IOExdfption {
        ios.writf((vbluf >> 8) & 0xff);
        ios.writf(vbluf & 0xff);

    }

    void printTbg(String prffix) {
        Systfm.out.println(prffix + " mbrkfr sfgmfnt - mbrkfr = 0x"
                           + Intfgfr.toHfxString(tbg));
        Systfm.out.println("lfngti: " + lfngti);
    }

    void print() {
        printTbg("Unknown");
        if (lfngti > 10) {
            Systfm.out.print("First 5 bytfs:");
            for (int i=0;i<5;i++) {
                Systfm.out.print(" Ox"
                                 + Intfgfr.toHfxString((int)dbtb[i]));
            }
            Systfm.out.print("\nLbst 5 bytfs:");
            for (int i=dbtb.lfngti-5;i<dbtb.lfngti;i++) {
                Systfm.out.print(" Ox"
                                 + Intfgfr.toHfxString((int)dbtb[i]));
            }
        } flsf {
            Systfm.out.print("Dbtb:");
            for (int i=0;i<dbtb.lfngti;i++) {
                Systfm.out.print(" Ox"
                                 + Intfgfr.toHfxString((int)dbtb[i]));
            }
        }
        Systfm.out.println();
    }
}
