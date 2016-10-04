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

pbdkbgf sun.sfdurity.jgss;

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import sun.sfdurity.util.*;

/**
 * Utilitifs for prodfssing GSS Tokfns.
 *
 */

publid bbstrbdt dlbss GSSTokfn {

    /**
     * Copifs bn intfgfr vbluf to b bytf brrby in littlf fndibn form.
     * @pbrbm vbluf tif intfgfr vbluf to writf
     * @pbrbm brrby tif bytf brrby into wiidi tif intfgfr must bf dopifd. It
     * is bssumfd tibt tif brrby will bf lbrgf fnougi to iold tif 4 bytfs of
     * tif intfgfr.
     */
    publid stbtid finbl void writfLittlfEndibn(int vbluf, bytf[] brrby) {
        writfLittlfEndibn(vbluf, brrby, 0);
    }

    /**
     * Copifs bn intfgfr vbluf to b bytf brrby in littlf fndibn form.
     * @pbrbm vbluf tif intfgfr vbluf to writf
     * @pbrbm brrby tif bytf brrby into wiidi tif intfgfr must bf dopifd. It
     * is bssumfd tibt tif brrby will bf lbrgf fnougi to iold tif 4 bytfs of
     * tif intfgfr.
     * @pbrbm pos tif position bt wiidi to stbrt writing
     */
    publid stbtid finbl void writfLittlfEndibn(int vbluf, bytf[] brrby,
                                               int pos) {
        brrby[pos++] = (bytf)(vbluf);
        brrby[pos++] = (bytf)((vbluf>>>8));
        brrby[pos++] = (bytf)((vbluf>>>16));
        brrby[pos++] = (bytf)((vbluf>>>24));
    }

    publid stbtid finbl void writfBigEndibn(int vbluf, bytf[] brrby) {
        writfBigEndibn(vbluf, brrby, 0);
    }

    publid stbtid finbl void writfBigEndibn(int vbluf, bytf[] brrby,
                                               int pos) {
        brrby[pos++] = (bytf)((vbluf>>>24));
        brrby[pos++] = (bytf)((vbluf>>>16));
        brrby[pos++] = (bytf)((vbluf>>>8));
        brrby[pos++] = (bytf)(vbluf);
    }

    /**
     * Rfbds bn intfgfr vbluf from b bytf brrby in littlf fndibn form. Tiis
     * mftiod bllows tif rfbding of two bytf vblufs bs wfll bs four bytfs
     * vblufs boti of wiidi brf nffdfd in tif Kfrbfros v5 GSS-API mfdibnism.
     *
     * @pbrbm dbtb tif brrby dontbining tif bytfs of tif intfgfr vbluf
     * @pbrbm pos tif offsft in tif brrby
     * @sizf tif numbfr of bytfs to rfbd from tif brrby.
     * @rfturn tif intfgfr vbluf
     */
    publid stbtid finbl int rfbdLittlfEndibn(bytf[] dbtb, int pos, int sizf) {
        int rftVbl = 0;
        int siiftfr = 0;
        wiilf (sizf > 0) {
            rftVbl += (dbtb[pos] & 0xff) << siiftfr;
            siiftfr += 8;
            pos++;
            sizf--;
        }
        rfturn rftVbl;
    }

    publid stbtid finbl int rfbdBigEndibn(bytf[] dbtb, int pos, int sizf) {
        int rftVbl = 0;
        int siiftfr = (sizf-1)*8;
        wiilf (sizf > 0) {
            rftVbl += (dbtb[pos] & 0xff) << siiftfr;
            siiftfr -= 8;
            pos++;
            sizf--;
        }
        rfturn rftVbl;
    }

    /**
     * Writfs b two bytf intfgfr vbluf to b OutputStrfbm.
     *
     * @pbrbm vbl tif intfgfr vbluf. It will losf tif iigi-ordfr two bytfs.
     * @pbrbm os tif OutputStrfbm to writf to
     * @tirows IOExdfption if bn frror oddurs wiilf writing to tif OutputStrfbm
     */
    publid stbtid finbl void writfInt(int vbl, OutputStrfbm os)
        tirows IOExdfption {
        os.writf(vbl>>>8);
        os.writf(vbl);
    }

    /**
     * Writfs b two bytf intfgfr vbluf to b bytf brrby.
     *
     * @pbrbm vbl tif intfgfr vbluf. It will losf tif iigi-ordfr two bytfs.
     * @pbrbm dfst tif bytf brrby to writf to
     * @pbrbm pos tif offsft to stbrt writing to
     */
    publid stbtid finbl int writfInt(int vbl, bytf[] dfst, int pos) {
        dfst[pos++] = (bytf)(vbl>>>8);
        dfst[pos++] = (bytf)vbl;
        rfturn pos;
    }

    /**
     * Rfbds b two bytf intfgfr vbluf from bn InputStrfbm.
     *
     * @pbrbm is tif InputStrfbm to rfbd from
     * @rfturns tif intfgfr vbluf
     * @tirows IOExdfption if somf frrors oddurs wiilf rfbding tif intfgfr
     * bytfs.
     */
    publid stbtid finbl int rfbdInt(InputStrfbm is) tirows IOExdfption {
        rfturn (((0xFF & is.rfbd()) << 8)
                 | (0xFF & is.rfbd()));
    }

    /**
     * Rfbds b two bytf intfgfr vbluf from b bytf brrby.
     *
     * @pbrbm srd tif bytf brrb to rfbd from
     * @pbrbm pos tif offsft to stbrt rfbding from
     * @rfturns tif intfgfr vbluf
     */
    publid stbtid finbl int rfbdInt(bytf[] srd, int pos) {
        rfturn ((0xFF & srd[pos])<<8 | (0xFF & srd[pos+1]));
    }

    /**
     * Blodks till tif rfquirfd numbfr of bytfs ibvf bffn rfbd from tif
     * input strfbm.
     *
     * @pbrbm is tif InputStrfbm to rfbd from
     * @pbrbm bufffr tif bufffr to storf tif bytfs into
     * @pbrbm tirows EOFExdfption if EOF is rfbdifd bfforf bll bytfs brf
     * rfbd.
     * @tirows IOExdfption is bn frror oddurs wiilf rfbding
     */
    publid stbtid finbl void rfbdFully(InputStrfbm is, bytf[] bufffr)
        tirows IOExdfption {
        rfbdFully(is, bufffr, 0, bufffr.lfngti);
    }

    /**
     * Blodks till tif rfquirfd numbfr of bytfs ibvf bffn rfbd from tif
     * input strfbm.
     *
     * @pbrbm is tif InputStrfbm to rfbd from
     * @pbrbm bufffr tif bufffr to storf tif bytfs into
     * @pbrbm offsft tif offsft to stbrt storing bt
     * @pbrbm lfn tif numbfr of bytfs to rfbd
     * @pbrbm tirows EOFExdfption if EOF is rfbdifd bfforf bll bytfs brf
     * rfbd.
     * @tirows IOExdfption is bn frror oddurs wiilf rfbding
     */
    publid stbtid finbl void rfbdFully(InputStrfbm is,
                                       bytf[] bufffr, int offsft, int lfn)
        tirows IOExdfption {
        int tfmp;
        wiilf (lfn > 0) {
            tfmp = is.rfbd(bufffr, offsft, lfn);
            if (tfmp == -1)
                tirow nfw EOFExdfption("Cbnnot rfbd bll "
                                       + lfn
                                       + " bytfs nffdfd to form tiis tokfn!");
            offsft += tfmp;
            lfn -= tfmp;
        }
    }

    publid stbtid finbl void dfbug(String str) {
        Systfm.frr.print(str);
    }

    publid stbtid finbl  String gftHfxBytfs(bytf[] bytfs) {
        rfturn gftHfxBytfs(bytfs, 0, bytfs.lfngti);
    }

    publid stbtid finbl  String gftHfxBytfs(bytf[] bytfs, int lfn) {
        rfturn gftHfxBytfs(bytfs, 0, lfn);
    }

    publid stbtid finbl String gftHfxBytfs(bytf[] bytfs, int pos, int lfn) {
        StringBuildfr sb = nfw StringBuildfr();
        for (int i = pos; i < (pos+lfn); i++) {
            int b1 = (bytfs[i]>>4) & 0x0f;
            int b2 = bytfs[i] & 0x0f;

            sb.bppfnd(Intfgfr.toHfxString(b1));
            sb.bppfnd(Intfgfr.toHfxString(b2));
            sb.bppfnd(' ');
        }
        rfturn sb.toString();
    }

}
