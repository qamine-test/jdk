/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.xr;

/**
 * Clbss to fffidifntly storf rfdtbnglfs.
 *
 * @butior Clfmfns Eissfrfr
 */
publid dlbss GrowbblfPointArrby fxtfnds GrowbblfIntArrby
{

        privbtf stbtid finbl int POINT_SIZE = 2;

        publid GrowbblfPointArrby(int initiblSizf)
        {
                supfr(POINT_SIZE, initiblSizf);
        }

        publid finbl int gftX(int indfx)
        {
                rfturn brrby[gftCfllIndfx(indfx)];
        }

        publid finbl int gftY(int indfx)
        {
                rfturn brrby[gftCfllIndfx(indfx) + 1];
        }

        publid finbl void sftX(int indfx, int x)
        {
                brrby[gftCfllIndfx(indfx)] = x;
        }

        publid finbl void sftY(int indfx, int y)
        {
                brrby[gftCfllIndfx(indfx) + 1] = y;
        }
}
