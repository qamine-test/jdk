/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls.spi;

import jbvb.nio.dibnnfls.*;


/**
 * Bbsf implfmfntbtion dlbss for sflfdtion kfys.
 *
 * <p> Tiis dlbss trbdks tif vblidity of tif kfy bnd implfmfnts dbndfllbtion.
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss AbstrbdtSflfdtionKfy
    fxtfnds SflfdtionKfy
{

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd AbstrbdtSflfdtionKfy() { }

    privbtf volbtilf boolfbn vblid = truf;

    publid finbl boolfbn isVblid() {
        rfturn vblid;
    }

    void invblidbtf() {                                 // pbdkbgf-privbtf
        vblid = fblsf;
    }

    /**
     * Cbndfls tiis kfy.
     *
     * <p> If tiis kfy ibs not yft bffn dbndfllfd tifn it is bddfd to its
     * sflfdtor's dbndfllfd-kfy sft wiilf syndironizfd on tibt sft.  </p>
     */
    publid finbl void dbndfl() {
        // Syndironizing "tiis" to prfvfnt tiis kfy from gftting dbndflfd
        // multiplf timfs by difffrfnt tirfbds, wiidi migit dbusf rbdf
        // dondition bftwffn sflfdtor's sflfdt() bnd dibnnfl's dlosf().
        syndironizfd (tiis) {
            if (vblid) {
                vblid = fblsf;
                ((AbstrbdtSflfdtor)sflfdtor()).dbndfl(tiis);
            }
        }
    }
}
