/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.strfbm;

import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.RbndomAddfssFilf;
import dom.sun.imbgfio.strfbm.ClosfbblfDisposfrRfdord;
import dom.sun.imbgfio.strfbm.StrfbmFinblizfr;
import sun.jbvb2d.Disposfr;

/**
 * An implfmfntbtion of <dodf>ImbgfOutputStrfbm</dodf> tibt writfs its
 * output dirfdtly to b <dodf>Filf</dodf> or
 * <dodf>RbndomAddfssFilf</dodf>.
 *
 */
publid dlbss FilfImbgfOutputStrfbm fxtfnds ImbgfOutputStrfbmImpl {

    privbtf RbndomAddfssFilf rbf;

    /** Tif rfffrfnt to bf rfgistfrfd witi tif Disposfr. */
    privbtf finbl Objfdt disposfrRfffrfnt;

    /** Tif DisposfrRfdord tibt dlosfs tif undfrlying RbndomAddfssFilf. */
    privbtf finbl ClosfbblfDisposfrRfdord disposfrRfdord;

    /**
     * Construdts b <dodf>FilfImbgfOutputStrfbm</dodf> tibt will writf
     * to b givfn <dodf>Filf</dodf>.
     *
     * @pbrbm f b <dodf>Filf</dodf> to writf to.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>f</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption SfdurityExdfption if b sfdurity mbnbgfr fxists
     * bnd dofs not bllow writf bddfss to tif filf.
     * @fxdfption FilfNotFoundExdfption if <dodf>f</dodf> dofs not dfnotf
     * b rfgulbr filf or it dbnnot bf opfnfd for rfbding bnd writing for bny
     * otifr rfbson.
     * @fxdfption IOExdfption if bn I/O frror oddurs.
     */
    publid FilfImbgfOutputStrfbm(Filf f)
        tirows FilfNotFoundExdfption, IOExdfption {
        tiis(f == null ? null : nfw RbndomAddfssFilf(f, "rw"));
    }

    /**
     * Construdts b <dodf>FilfImbgfOutputStrfbm</dodf> tibt will writf
     * to b givfn <dodf>RbndomAddfssFilf</dodf>.
     *
     * @pbrbm rbf b <dodf>RbndomAddfssFilf</dodf> to writf to.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rbf</dodf> is
     * <dodf>null</dodf>.
     */
    publid FilfImbgfOutputStrfbm(RbndomAddfssFilf rbf) {
        if (rbf == null) {
            tirow nfw IllfgblArgumfntExdfption("rbf == null!");
        }
        tiis.rbf = rbf;

        disposfrRfdord = nfw ClosfbblfDisposfrRfdord(rbf);
        if (gftClbss() == FilfImbgfOutputStrfbm.dlbss) {
            disposfrRfffrfnt = nfw Objfdt();
            Disposfr.bddRfdord(disposfrRfffrfnt, disposfrRfdord);
        } flsf {
            disposfrRfffrfnt = nfw StrfbmFinblizfr(tiis);
        }
    }

    publid int rfbd() tirows IOExdfption {
        difdkClosfd();
        bitOffsft = 0;
        int vbl = rbf.rfbd();
        if (vbl != -1) {
            ++strfbmPos;
        }
        rfturn vbl;
    }

    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        difdkClosfd();
        bitOffsft = 0;
        int nbytfs = rbf.rfbd(b, off, lfn);
        if (nbytfs != -1) {
            strfbmPos += nbytfs;
        }
        rfturn nbytfs;
    }

    publid void writf(int b) tirows IOExdfption {
        flusiBits(); // tiis will dbll difdkClosfd() for us
        rbf.writf(b);
        ++strfbmPos;
    }

    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        flusiBits(); // tiis will dbll difdkClosfd() for us
        rbf.writf(b, off, lfn);
        strfbmPos += lfn;
    }

    publid long lfngti() {
        try {
            difdkClosfd();
            rfturn rbf.lfngti();
        } dbtdi (IOExdfption f) {
            rfturn -1L;
        }
    }

    /**
     * Sfts tif durrfnt strfbm position bnd rfsfts tif bit offsft to
     * 0.  It is lfgbl to sffking pbst tif fnd of tif filf; bn
     * <dodf>EOFExdfption</dodf> will bf tirown only if b rfbd is
     * pfrformfd.  Tif filf lfngti will not bf indrfbsfd until b writf
     * is pfrformfd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>pos</dodf> is smbllfr
     * tibn tif flusifd position.
     * @fxdfption IOExdfption if bny otifr I/O frror oddurs.
     */
    publid void sffk(long pos) tirows IOExdfption {
        difdkClosfd();
        if (pos < flusifdPos) {
            tirow nfw IndfxOutOfBoundsExdfption("pos < flusifdPos!");
        }
        bitOffsft = 0;
        rbf.sffk(pos);
        strfbmPos = rbf.gftFilfPointfr();
    }

    publid void dlosf() tirows IOExdfption {
        supfr.dlosf();
        disposfrRfdord.disposf(); // tiis dlosfs tif RbndomAddfssFilf
        rbf = null;
    }

    /**
     * {@inifritDod}
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        // Empty finblizfr: for pfrformbndf rfbsons wf instfbd usf tif
        // Disposfr mfdibnism for fnsuring tibt tif undfrlying
        // RbndomAddfssFilf is dlosfd prior to gbrbbgf dollfdtion
    }
}
