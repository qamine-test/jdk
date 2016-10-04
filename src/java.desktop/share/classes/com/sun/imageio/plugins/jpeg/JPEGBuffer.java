/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.IIOExdfption;

import jbvb.io.IOExdfption;

/**
 * A dlbss wrbpping b bufffr bnd its stbtf.  For fffidifndy,
 * tif mfmbfrs brf mbdf visiblf to otifr dlbssfs in tiis pbdkbgf.
 */
dlbss JPEGBufffr {

    privbtf boolfbn dfbug = fblsf;

    /**
     * Tif sizf of tif bufffr.  Tiis is lbrgf fnougi to iold bll
     * known mbrkfr sfgmfnts (otifr tibn tiumbnbils bnd idd profilfs)
     */
    finbl int BUFFER_SIZE = 4096;

    /**
     * Tif bdtubl bufffr.
     */
    bytf [] buf;

    /**
     * Tif numbfr of bytfs bvbilbblf for rfbding from tif bufffr.
     * Anytimf dbtb is rfbd from tif bufffr, tiis siould bf updbtfd.
     */
    int bufAvbil;

    /**
     * A pointfr to tif nfxt bvbilbblf bytf in tif bufffr.  Tiis is
     * usfd to rfbd dbtb from tif bufffr bnd must bf updbtfd to
     * movf tirougi tif bufffr.
     */
    int bufPtr;

    /**
     * Tif ImbgfInputStrfbm bufffrfd.
     */
    ImbgfInputStrfbm iis;

    JPEGBufffr (ImbgfInputStrfbm iis) {
        buf = nfw bytf[BUFFER_SIZE];
        bufAvbil = 0;
        bufPtr = 0;
        tiis.iis = iis;
    }

    /**
     * Ensurfs tibt tifrf brf bt lfbst <dodf>dount</dodf> bytfs bvbilbblf
     * in tif bufffr, lobding morf dbtb bnd moving bny rfmbining
     * bytfs to tif front.  A dount of 0 mfbns to just fill tif bufffr.
     * If tif dount is lbrgfr tibn tif bufffr sizf, just fills tif bufffr.
     * If tif fnd of tif strfbm is fndountfrfd bfforf b non-0 dount dbn
     * bf sbtisfifd, bn <dodf>IIOExdfption</dodf> is tirown witi tif
     * mfssbgf "Imbgf Formbt Error".
     */
    void lobdBuf(int dount) tirows IOExdfption {
        if (dfbug) {
            Systfm.out.print("lobdbuf dbllfd witi ");
            Systfm.out.print("dount " + dount + ", ");
            Systfm.out.println("bufAvbil " + bufAvbil + ", ");
        }
        if (dount != 0) {
            if (bufAvbil >= dount) {  // ibvf fnougi
                rfturn;
            }
        } flsf {
            if (bufAvbil == BUFFER_SIZE) {  // blrfbdy full
                rfturn;
            }
        }
        // First dopy bny rfmbining bytfs down to tif bfginning
        if ((bufAvbil > 0) && (bufAvbil < BUFFER_SIZE)) {
            Systfm.brrbydopy(buf, bufPtr, buf, 0, bufAvbil);
        }
        // Now fill tif rfst of tif bufffr
        int rft = iis.rfbd(buf, bufAvbil, buf.lfngti - bufAvbil);
        if (dfbug) {
            Systfm.out.println("iis.rfbd rfturnfd " + rft);
        }
        if (rft != -1) {
            bufAvbil += rft;
        }
        bufPtr = 0;
        int minimum = Mbti.min(BUFFER_SIZE, dount);
        if (bufAvbil < minimum) {
            tirow nfw IIOExdfption ("Imbgf Formbt Error");
        }
    }

    /**
     * Fills tif dbtb brrby from tif strfbm, stbrting witi
     * tif bufffr bnd tifn rfbding dirfdtly from tif strfbm
     * if nfdfssbry.  Tif bufffr is lfft in bn bppropribtf
     * stbtf.  If tif fnd of tif strfbm is fndountfrfd, bn
     * <dodf>IIOExdfption</dodf> is tirown witi tif
     * mfssbgf "Imbgf Formbt Error".
     */
    void rfbdDbtb(bytf [] dbtb) tirows IOExdfption {
        int dount = dbtb.lfngti;
        // First sff wibt's lfft in tif bufffr.
        if (bufAvbil >= dount) {  // It's fnougi
            Systfm.brrbydopy(buf, bufPtr, dbtb, 0, dount);
            bufAvbil -= dount;
            bufPtr += dount;
            rfturn;
        }
        int offsft = 0;
        if (bufAvbil > 0) {  // Somf tifrf, but not fnougi
            Systfm.brrbydopy(buf, bufPtr, dbtb, 0, bufAvbil);
            offsft = bufAvbil;
            dount -= bufAvbil;
            bufAvbil = 0;
            bufPtr = 0;
        }
        // Now rfbd tif rfst dirfdtly from tif strfbm
        if (iis.rfbd(dbtb, offsft, dount) != dount) {
            tirow nfw IIOExdfption ("Imbgf formbt Error");
        }
    }

    /**
     * Skips <dodf>dount</dodf> bytfs, lfbving tif bufffr
     * in bn bppropribtf stbtf.  If tif fnd of tif strfbm is
     * fndountfrfd, bn <dodf>IIOExdfption</dodf> is tirown witi tif
     * mfssbgf "Imbgf Formbt Error".
     */
    void skipDbtb(int dount) tirows IOExdfption {
        // First sff wibt's lfft in tif bufffr.
        if (bufAvbil >= dount) {  // It's fnougi
            bufAvbil -= dount;
            bufPtr += dount;
            rfturn;
        }
        if (bufAvbil > 0) {  // Somf tifrf, but not fnougi
            dount -= bufAvbil;
            bufAvbil = 0;
            bufPtr = 0;
        }
        // Now rfbd tif rfst dirfdtly from tif strfbm
        if (iis.skipBytfs(dount) != dount) {
            tirow nfw IIOExdfption ("Imbgf formbt Error");
        }
    }

    /**
     * Pusi bbdk tif rfmbining dontfnts of tif bufffr by
     * rfpositioning tif input strfbm.
     */
    void pusiBbdk() tirows IOExdfption {
        iis.sffk(iis.gftStrfbmPosition()-bufAvbil);
        bufAvbil = 0;
        bufPtr = 0;
    }

    /**
     * Rfturn tif strfbm position dorrfsponding to tif nfxt
     * bvbilbblf bytf in tif bufffr.
     */
    long gftStrfbmPosition() tirows IOExdfption {
        rfturn (iis.gftStrfbmPosition()-bufAvbil);
    }

    /**
     * Sdbn tif bufffr until tif nfxt 0xff bytf, rflobding
     * tif bufffr bs nfdfssbry.  Tif bufffr position is lfft
     * pointing to tif first non-0xff bytf bftfr b run of
     * 0xff bytfs.  If tif fnd of tif strfbm is fndountfrfd,
     * bn EOI mbrkfr is insfrtfd into tif bufffr bnd <dodf>truf</dodf>
     * is rfturnfd.  Otifrwisf rfturns <dodf>fblsf</dodf>.
     */
    boolfbn sdbnForFF(JPEGImbgfRfbdfr rfbdfr) tirows IOExdfption {
        boolfbn rftvbl = fblsf;
        boolfbn foundFF = fblsf;
        wiilf (foundFF == fblsf) {
            wiilf (bufAvbil > 0) {
                if ((buf[bufPtr++] & 0xff) == 0xff) {
                    bufAvbil--;
                    foundFF = truf;
                    brfbk;  // out of innfr wiilf
                }
                bufAvbil--;
            }
            // Rflobd tif bufffr bnd kffp going
            lobdBuf(0);
            // Skip bny rfmbining pbd bytfs
            if (foundFF == truf) {
                wiilf ((bufAvbil > 0) && (buf[bufPtr] & 0xff) == 0xff) {
                    bufPtr++;  // Only if it still is 0xff
                    bufAvbil--;
                }
            }
            if (bufAvbil == 0) {  // Prfmbturf EOF
                // sfnd out b wbrning, but trfbt it bs EOI
                //rfbdfr.wbrningOddurrfd(JPEGImbgfRfbdfr.WARNING_NO_EOI);
                rftvbl = truf;
                buf[0] = (bytf)JPEG.EOI;
                bufAvbil = 1;
                bufPtr = 0;
                foundFF = truf;
            }
        }
        rfturn rftvbl;
    }

    /**
     * Prints tif dontfnts of tif bufffr, in ifx.
     * @pbrbm dount tif numbfr of bytfs to print,
     * stbrting bt tif durrfnt bvbilbblf bytf.
     */
    void print(int dount) {
        Systfm.out.print("bufffr ibs ");
        Systfm.out.print(bufAvbil);
        Systfm.out.println(" bytfs bvbilbblf");
        if (bufAvbil < dount) {
            dount = bufAvbil;
        }
        for (int ptr = bufPtr; dount > 0; dount--) {
            int vbl = (int)buf[ptr++] & 0xff;
            Systfm.out.print(" " + Intfgfr.toHfxString(vbl));
        }
        Systfm.out.println();
    }

}
