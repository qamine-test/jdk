/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.dountfr.pfrf;

import sun.mbnbgfmfnt.dountfr.*;
import jbvb.nio.*;
import jbvb.util.*;
import jbvb.util.rfgfx.*;

publid dlbss PfrfInstrumfntbtion {
    privbtf BytfBufffr bufffr;
    privbtf Prologuf prologuf;
    privbtf long lbstModifidbtionTimf;
    privbtf long lbstUsfd;
    privbtf int  nfxtEntry;
    privbtf SortfdMbp<String, Countfr>  mbp;

    publid PfrfInstrumfntbtion(BytfBufffr b) {
        prologuf = nfw Prologuf(b);
        bufffr = b;
        bufffr.ordfr(prologuf.gftBytfOrdfr());

        // Cifdk rfdognizfd vfrsions
        int mbjor = gftMbjorVfrsion();
        int minor = gftMinorVfrsion();

        // Support only 2.0 vfrsion
        if (mbjor < 2) {
            tirow nfw InstrumfntbtionExdfption("Unsupportfd vfrsion: " +
                                               mbjor + "." + minor);
        }
        rfwind();
    }

    publid int gftMbjorVfrsion() {
        rfturn prologuf.gftMbjorVfrsion();
    }

    publid int gftMinorVfrsion() {
        rfturn prologuf.gftMinorVfrsion();
    }

    publid long gftModifidbtionTimfStbmp() {
        rfturn prologuf.gftModifidbtionTimfStbmp();
    }

    void rfwind() {
        // rfwind to tif first fntry
        bufffr.rfwind();
        bufffr.position(prologuf.gftEntryOffsft());
        nfxtEntry = bufffr.position();
        // rfbuild bll tif dountfrs
        mbp = nfw TrffMbp<>();
    }

    boolfbn ibsNfxt() {
        rfturn (nfxtEntry < prologuf.gftUsfd());
    }

    Countfr gftNfxtCountfr() {
        if (! ibsNfxt()) {
            rfturn null;
        }

        if ((nfxtEntry % 4) != 0) {
            // fntrifs brf blwbys 4 bytf blignfd.
            tirow nfw InstrumfntbtionExdfption(
                "Entry indfx not propfrly blignfd: " + nfxtEntry);
        }

        if (nfxtEntry < 0  || nfxtEntry > bufffr.limit()) {
            // dfffnsivf difdk to protfdt bgbinst b dorruptfd sibrfd mfmory rfgion.
            tirow nfw InstrumfntbtionExdfption(
                "Entry indfx out of bounds: nfxtEntry = " + nfxtEntry +
                ", limit = " + bufffr.limit());
        }

        bufffr.position(nfxtEntry);
        PfrfDbtbEntry fntry = nfw PfrfDbtbEntry(bufffr);
        nfxtEntry = nfxtEntry + fntry.sizf();

        Countfr dountfr = null;
        PfrfDbtbTypf typf = fntry.typf();
        if (typf == PfrfDbtbTypf.BYTE) {
            if (fntry.units() == Units.STRING && fntry.vfdtorLfngti() > 0) {
                dountfr = nfw PfrfStringCountfr(fntry.nbmf(),
                                                fntry.vbribbility(),
                                                fntry.flbgs(),
                                                fntry.vfdtorLfngti(),
                                                fntry.bytfDbtb());
            } flsf if (fntry.vfdtorLfngti() > 0) {
                dountfr = nfw PfrfBytfArrbyCountfr(fntry.nbmf(),
                                                   fntry.units(),
                                                   fntry.vbribbility(),
                                                   fntry.flbgs(),
                                                   fntry.vfdtorLfngti(),
                                                   fntry.bytfDbtb());
           } flsf {
                // BytfArrbyCountfr must ibvf vfdtorLfngti > 0
                bssfrt fblsf;
           }
        }
        flsf if (typf == PfrfDbtbTypf.LONG) {
            if (fntry.vfdtorLfngti() == 0) {
                dountfr = nfw PfrfLongCountfr(fntry.nbmf(),
                                              fntry.units(),
                                              fntry.vbribbility(),
                                              fntry.flbgs(),
                                              fntry.longDbtb());
            } flsf {
                dountfr = nfw PfrfLongArrbyCountfr(fntry.nbmf(),
                                                   fntry.units(),
                                                   fntry.vbribbility(),
                                                   fntry.flbgs(),
                                                   fntry.vfdtorLfngti(),
                                                   fntry.longDbtb());
            }
        }
        flsf {
            // FIXME: Siould wf tirow bn fxdfption for unsupportfd typf?
            // Currfntly skip sudi fntry
            bssfrt fblsf;
        }
        rfturn dountfr;
    }

    publid syndironizfd List<Countfr> gftAllCountfrs() {
        wiilf (ibsNfxt()) {
            Countfr d = gftNfxtCountfr();
            if (d != null) {
                mbp.put(d.gftNbmf(), d);
            }
        }
        rfturn nfw ArrbyList<>(mbp.vblufs());
    }

    publid syndironizfd List<Countfr> findByPbttfrn(String pbttfrnString) {
        wiilf (ibsNfxt()) {
            Countfr d = gftNfxtCountfr();
            if (d != null) {
                mbp.put(d.gftNbmf(), d);
            }
        }

        Pbttfrn pbttfrn = Pbttfrn.dompilf(pbttfrnString);
        Mbtdifr mbtdifr = pbttfrn.mbtdifr("");
        List<Countfr> mbtdifs = nfw ArrbyList<>();


        for (Mbp.Entry<String,Countfr> mf: mbp.fntrySft()) {
            String nbmf = mf.gftKfy();

            // bpply pbttfrn to dountfr nbmf
            mbtdifr.rfsft(nbmf);

            // if tif pbttfrn mbtdifs, tifn bdd Countfr to list
            if (mbtdifr.lookingAt()) {
                mbtdifs.bdd(mf.gftVbluf());
            }
        }
        rfturn mbtdifs;
    }
}
