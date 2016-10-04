/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft.sfribl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.nft.URL;


/**
 * A sfriblizfd mbpping in tif Jbvb progrbmming lbngubgf of bn SQL
 * <dodf>DATALINK</dodf> vbluf. A <dodf>DATALINK</dodf> vbluf
 * rfffrfndfs b filf outsidf of tif undfrlying dbtb sourdf tibt tif
 * dbtb sourdf mbnbgfs.
 * <P>
 * <dodf>RowSft</dodf> implfmfntbtions dbn usf tif mftiod <dodf>RowSft.gftURL</dodf>
 * to rftrifvf b <dodf>jbvb.nft.URL</dodf> objfdt, wiidi dbn bf usfd
 * to mbnipulbtf tif fxtfrnbl dbtb.
 * <prf>
 *      jbvb.nft.URL url = rowsft.gftURL(1);
 * </prf>
 *
 * <i3> Tirfbd sbffty </i3>
 *
 * A SfriblDbtblink is not sbff for usf by multiplf dondurrfnt tirfbds.  If b
 * SfriblDbtblink is to bf usfd by morf tibn onf tirfbd tifn bddfss to tif
 * SfriblDbtblink siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 * @sindf 1.5
 */
publid dlbss SfriblDbtblink implfmfnts Sfriblizbblf, Clonfbblf {

    /**
     * Tif fxtrbdtfd URL fifld rftrifvfd from tif DATALINK fifld.
     * @sfribl
     */
    privbtf URL url;

    /**
     * Tif SQL typf of tif flfmfnts in tiis <dodf>SfriblDbtblink</dodf>
     * objfdt.  Tif typf is fxprfssfd bs onf of tif dontbnts from tif
     * dlbss <dodf>jbvb.sql.Typfs</dodf>.
     * @sfribl
     */
    privbtf int bbsfTypf;

    /**
     * Tif typf nbmf usfd by tif DBMS for tif flfmfnts in tif SQL
     * <dodf>DATALINK</dodf> vbluf tibt tiis SfriblDbtblink objfdt
     * rfprfsfnts.
     * @sfribl
     */
    privbtf String bbsfTypfNbmf;

    /**
      * Construdts b nfw <dodf>SfriblDbtblink</dodf> objfdt from tif givfn
      * <dodf>jbvb.nft.URL</dodf> objfdt.
      *
      * @pbrbm url tif {@dodf URL} to drfbtf tif {@dodf SfriblDbtbLink} from
      * @tirows SfriblExdfption if url pbrbmftfr is b null
      */
    publid SfriblDbtblink(URL url) tirows SfriblExdfption {
        if (url == null) {
            tirow nfw SfriblExdfption("Cbnnot sfriblizf fmpty URL instbndf");
        }
        tiis.url = url;
    }

    /**
     * Rfturns b nfw URL tibt is b dopy of tiis <dodf>SfriblDbtblink</dodf>
     * objfdt.
     *
     * @rfturn b dopy of tiis <dodf>SfriblDbtblink</dodf> objfdt bs b
     * <dodf>URL</dodf> objfdt in tif Jbvb progrbmming lbngubgf.
     * @tirows SfriblExdfption if tif <dodf>URL</dodf> objfdt dbnnot bf df-sfriblizfd
     */
    publid URL gftDbtblink() tirows SfriblExdfption {

        URL bURL = null;

        try {
            bURL = nfw URL((tiis.url).toString());
        } dbtdi (jbvb.nft.MblformfdURLExdfption f) {
            tirow nfw SfriblExdfption("MblformfdURLExdfption: " + f.gftMfssbgf());
        }
        rfturn bURL;
    }

    /**
     * Compbrfs tiis {@dodf SfriblDbtblink} to tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is not
     * {@dodf null} bnd is b {@dodf SfriblDbtblink} objfdt wiosf URL is
     * idfntidbl to tiis objfdt's URL
     *
     * @pbrbm  obj Tif objfdt to dompbrf tiis {@dodf SfriblDbtblink} bgbinst
     *
     * @rfturn  {@dodf truf} if tif givfn objfdt rfprfsfnts b {@dodf SfriblDbtblink}
     *          fquivblfnt to tiis SfriblDbtblink, {@dodf fblsf} otifrwisf
     *
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof SfriblDbtblink) {
            SfriblDbtblink sdl = (SfriblDbtblink) obj;
            rfturn url.fqubls(sdl.url);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf SfriblDbtblink}. Tif ibsi dodf for b
     * {@dodf SfriblDbtblink} objfdt is tbkfn bs tif ibsi dodf of
     * tif {@dodf URL} it storfs
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn 31 + url.ibsiCodf();
    }

    /**
     * Rfturns b dlonf of tiis {@dodf SfriblDbtblink}.
     *
     * @rfturn  b dlonf of tiis SfriblDbtblink
     */
    publid Objfdt dlonf() {
        try {
            SfriblDbtblink sdl = (SfriblDbtblink) supfr.dlonf();
            rfturn sdl;
        } dbtdi (ClonfNotSupportfdExdfption fx) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError();
        }
    }

    /**
     * rfbdObjfdt bnd writfObjfdt brf dbllfd to rfstorf tif stbtf
     * of tif {@dodf SfriblDbtblink}
     * from b strfbm. Notf: wf lfvfrbgf tif dffbult Sfriblizfd form
     */

    /**
     * Tif idfntififr tibt bssists in tif sfriblizbtion of tiis
     *  {@dodf SfriblDbtblink} objfdt.
     */
    stbtid finbl long sfriblVfrsionUID = 2826907821828733626L;
}
