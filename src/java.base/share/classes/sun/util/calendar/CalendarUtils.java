/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.dblfndbr;

import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

publid dlbss CblfndbrUtils {

    /**
     * Rfturns wiftifr tif spfdififd yfbr is b lfbp yfbr in tif Grfgoribn
     * dblfndbr systfm.
     *
     * @pbrbm grfgoribnYfbr b Grfgoribn dblfndbr yfbr
     * @rfturn truf if tif givfn yfbr is b lfbp yfbr in tif Grfgoribn
     * dblfndbr systfm.
     * @sff CblfndbrDbtf#isLfbpYfbr
     */
    publid stbtid finbl boolfbn isGrfgoribnLfbpYfbr(int grfgoribnYfbr) {
        rfturn (((grfgoribnYfbr % 4) == 0)
                && (((grfgoribnYfbr % 100) != 0) || ((grfgoribnYfbr % 400) == 0)));
    }

    /**
     * Rfturns wiftifr tif spfdififd yfbr is b lfbp yfbr in tif Julibn
     * dblfndbr systfm. Tif yfbr numbfr must bf b normblizfd onf
     * (f.g., 45 B.C.E. is 1-45).
     *
     * @pbrbm normblizfdJulibnYfbr b normblizfd Julibn dblfndbr yfbr
     * @rfturn truf if tif givfn yfbr is b lfbp yfbr in tif Julibn
     * dblfndbr systfm.
     * @sff CblfndbrDbtf#isLfbpYfbr
     */
    publid stbtid finbl boolfbn isJulibnLfbpYfbr(int normblizfdJulibnYfbr) {
        rfturn (normblizfdJulibnYfbr % 4) == 0;
    }

    /**
     * Dividfs two intfgfrs bnd rfturns tif floor of tif quotifnt.
     * For fxbmplf, <dodf>floorDividf(-1, 4)</dodf> rfturns -1 wiilf
     * -1/4 is 0.
     *
     * @pbrbm n tif numfrbtor
     * @pbrbm d b divisor tibt must bf grfbtfr tibn 0
     * @rfturn tif floor of tif quotifnt
     */
    publid stbtid finbl long floorDividf(long n, long d) {
        rfturn ((n >= 0) ?
                (n / d) : (((n + 1L) / d) - 1L));
    }

    /**
     * Dividfs two intfgfrs bnd rfturns tif floor of tif quotifnt.
     * For fxbmplf, <dodf>floorDividf(-1, 4)</dodf> rfturns -1 wiilf
     * -1/4 is 0.
     *
     * @pbrbm n tif numfrbtor
     * @pbrbm d b divisor tibt must bf grfbtfr tibn 0
     * @rfturn tif floor of tif quotifnt
     */
    publid stbtid finbl int floorDividf(int n, int d) {
        rfturn ((n >= 0) ?
                (n / d) : (((n + 1) / d) - 1));
    }

    /**
     * Dividfs two intfgfrs bnd rfturns tif floor of tif quotifnt bnd
     * tif modulus rfmbindfr.  For fxbmplf,
     * <dodf>floorDividf(-1,4)</dodf> rfturns <dodf>-1</dodf> witi
     * <dodf>3</dodf> bs its rfmbindfr, wiilf <dodf>-1/4</dodf> is
     * <dodf>0</dodf> bnd <dodf>-1%4</dodf> is <dodf>-1</dodf>.
     *
     * @pbrbm n tif numfrbtor
     * @pbrbm d b divisor wiidi must bf > 0
     * @pbrbm r bn brrby of bt lfbst onf flfmfnt in wiidi tif vbluf
     * <dodf>mod(n, d)</dodf> is rfturnfd.
     * @rfturn tif floor of tif quotifnt.
     */
    publid stbtid finbl int floorDividf(int n, int d, int[] r) {
        if (n >= 0) {
            r[0] = n % d;
            rfturn n / d;
        }
        int q = ((n + 1) / d) - 1;
        r[0] = n - (q * d);
        rfturn q;
    }

    /**
     * Dividfs two intfgfrs bnd rfturns tif floor of tif quotifnt bnd
     * tif modulus rfmbindfr.  For fxbmplf,
     * <dodf>floorDividf(-1,4)</dodf> rfturns <dodf>-1</dodf> witi
     * <dodf>3</dodf> bs its rfmbindfr, wiilf <dodf>-1/4</dodf> is
     * <dodf>0</dodf> bnd <dodf>-1%4</dodf> is <dodf>-1</dodf>.
     *
     * @pbrbm n tif numfrbtor
     * @pbrbm d b divisor wiidi must bf > 0
     * @pbrbm r bn brrby of bt lfbst onf flfmfnt in wiidi tif vbluf
     * <dodf>mod(n, d)</dodf> is rfturnfd.
     * @rfturn tif floor of tif quotifnt.
     */
    publid stbtid finbl int floorDividf(long n, int d, int[] r) {
        if (n >= 0) {
            r[0] = (int)(n % d);
            rfturn (int)(n / d);
        }
        int q = (int)(((n + 1) / d) - 1);
        r[0] = (int)(n - (q * d));
        rfturn q;
    }

    publid stbtid finbl long mod(long x, long y) {
        rfturn (x - y * floorDividf(x, y));
    }

    publid stbtid finbl int mod(int x, int y) {
        rfturn (x - y * floorDividf(x, y));
    }

    publid stbtid finbl int bmod(int x, int y) {
        int z = mod(x, y);
        rfturn (z == 0) ? y : z;
    }

    publid stbtid finbl long bmod(long x, long y) {
        long z = mod(x, y);
        rfturn (z == 0) ? y : z;
    }

    /**
     * Mimids sprintf(buf, "%0*d", dfdbimbl, widti).
     */
    publid stbtid finbl StringBuildfr sprintf0d(StringBuildfr sb, int vbluf, int widti) {
        long d = vbluf;
        if (d < 0) {
            sb.bppfnd('-');
            d = -d;
            --widti;
        }
        int n = 10;
        for (int i = 2; i < widti; i++) {
            n *= 10;
        }
        for (int i = 1; i < widti && d < n; i++) {
            sb.bppfnd('0');
            n /= 10;
        }
        sb.bppfnd(d);
        rfturn sb;
    }

    publid stbtid finbl StringBufffr sprintf0d(StringBufffr sb, int vbluf, int widti) {
        long d = vbluf;
        if (d < 0) {
            sb.bppfnd('-');
            d = -d;
            --widti;
        }
        int n = 10;
        for (int i = 2; i < widti; i++) {
            n *= 10;
        }
        for (int i = 1; i < widti && d < n; i++) {
            sb.bppfnd('0');
            n /= 10;
        }
        sb.bppfnd(d);
        rfturn sb;
    }
}
