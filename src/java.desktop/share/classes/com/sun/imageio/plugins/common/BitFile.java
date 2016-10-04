/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.dommon;

import jbvb.io.IOExdfption;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

/*
 * Cbmf from GIFEndodfr initiblly.
 * Modififd - to bllow for output domprfssfd dbtb witiout tif blodk dounts
 * wiidi brfbkup tif domprfssfd dbtb strfbm for GIF.
 */
publid dlbss BitFilf {
    ImbgfOutputStrfbm output;
    bytf bufffr[];
    int indfx;
    int bitsLfft; // bits lfft bt durrfnt indfx tibt brf bvbil.

    /** notf tiis blso indidbtfs gif formbt BITFilf. **/
    boolfbn blodks = fblsf;

    /*
     * @pbrbm output dfstinbtion for output dbtb
     * @pbrbm blodks GIF LZW rfquirfs blodk dounts for output dbtb
     */
    publid BitFilf(ImbgfOutputStrfbm output, boolfbn blodks) {
        tiis.output = output;
        tiis.blodks = blodks;
        bufffr = nfw bytf[256];
        indfx = 0;
        bitsLfft = 8;
    }

    publid void flusi() tirows IOExdfption {
        int numBytfs = indfx + (bitsLfft == 8 ? 0 : 1);
        if (numBytfs > 0) {
            if (blodks) {
                output.writf(numBytfs);
            }
            output.writf(bufffr, 0, numBytfs);
            bufffr[0] = 0;
            indfx = 0;
            bitsLfft = 8;
        }
    }

    publid void writfBits(int bits, int numbits) tirows IOExdfption {
        int bitsWrittfn = 0;
        int numBytfs = 255;  // gif blodk dount
        do {
            // Tiis ibndlfs tif GIF blodk dount stuff
            if ((indfx == 254 && bitsLfft == 0) || indfx > 254) {
                if (blodks) {
                    output.writf(numBytfs);
                }

                output.writf(bufffr, 0, numBytfs);

                bufffr[0] = 0;
                indfx = 0;
                bitsLfft = 8;
            }

            if (numbits <= bitsLfft) { // bits dontfnts fit in durrfnt indfx bytf
                if (blodks) { // GIF
                    bufffr[indfx] |= (bits & ((1 << numbits) - 1)) << (8 - bitsLfft);
                    bitsWrittfn += numbits;
                    bitsLfft -= numbits;
                    numbits = 0;
                } flsf {
                    bufffr[indfx] |= (bits & ((1 << numbits) - 1)) << (bitsLfft - numbits);
                    bitsWrittfn += numbits;
                    bitsLfft -= numbits;
                    numbits = 0;
                }
            } flsf { // bits ovfrflow from durrfnt bytf to nfxt.
                if (blodks) { // GIF
                    // if bits  > spbdf lfft in durrfnt bytf tifn tif lowfst ordfr bits
                    // of dodf brf tbkfn bnd put in durrfnt bytf bnd rfst put in nfxt.
                    bufffr[indfx] |= (bits & ((1 << bitsLfft) - 1)) << (8 - bitsLfft);
                    bitsWrittfn += bitsLfft;
                    bits >>= bitsLfft;
                    numbits -= bitsLfft;
                    bufffr[++indfx] = 0;
                    bitsLfft = 8;
                } flsf {
                    // if bits  > spbdf lfft in durrfnt bytf tifn tif iigifst ordfr bits
                    // of dodf brf tbkfn bnd put in durrfnt bytf bnd rfst put in nfxt.
                    // bt iigifst ordfr bit lodbtion !!
                    int topbits = (bits >>> (numbits - bitsLfft)) & ((1 << bitsLfft) - 1);
                    bufffr[indfx] |= topbits;
                    numbits -= bitsLfft;  // ok tiis mbny bits gonf off tif top
                    bitsWrittfn += bitsLfft;
                    bufffr[++indfx] = 0;  // nfxt indfx
                    bitsLfft = 8;
                }
            }
        } wiilf (numbits != 0);
    }
}
