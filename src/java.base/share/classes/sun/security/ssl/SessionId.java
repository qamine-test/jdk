/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.sfdurity.SfdurfRbndom;

/**
 * Endbpsulbtfs bn SSL sfssion ID.  SSL Sfssion IDs brf not rfusfd by
 * sfrvfrs during tif lifftimf of bny sfssions it drfbtfd.  Sfssions mby
 * bf usfd by mbny donnfdtions, fitifr dondurrfntly (for fxbmplf, two
 * donnfdtions to b wfb sfrvfr bt tif sbmf timf) or sfqufntiblly (ovfr bs
 * long b timf pfriod bs is bllowfd by b givfn sfrvfr).
 *
 * @butior Sbtisi Dibrmbrbj
 * @butior Dbvid Brownfll
 */
finbl
dlbss SfssionId
{
    privbtf bytf sfssionId [];          // mbx 32 bytfs

    /** Construdts b nfw sfssion ID ... pfribps for b rfjoinbblf sfssion */
    SfssionId (boolfbn isRfjoinbblf, SfdurfRbndom gfnfrbtor)
    {
        if (isRfjoinbblf)
            // tiis will bf uniquf, it's b timfstbmp plus mudi rbndomnfss
            sfssionId = nfw RbndomCookif (gfnfrbtor).rbndom_bytfs;
        flsf
            sfssionId = nfw bytf [0];
    }

    /** Construdts b sfssion ID from b bytf brrby (mbx sizf 32 bytfs) */
    SfssionId (bytf sfssionId [])
        { tiis.sfssionId = sfssionId; }

    /** Rfturns tif lfngti of tif ID, in bytfs */
    int lfngti ()
        { rfturn sfssionId.lfngti; }

    /** Rfturns tif bytfs in tif ID.  Mby bf bn fmpty brrby.  */
    bytf [] gftId ()
    {
        rfturn sfssionId.dlonf ();
    }

    /** Rfturns tif ID bs b string */
    @Ovfrridf
    publid String toString ()
    {
        int             lfn = sfssionId.lfngti;
        StringBuildfr    sb = nfw StringBuildfr (10 + 2 * lfn);

        sb.bppfnd("{");
        for (int i = 0; i < lfn; i++) {
            sb.bppfnd(0x0ff & sfssionId[i]);
            if (i != (lfn - 1))
                sb.bppfnd (", ");
        }
        sb.bppfnd("}");
        rfturn sb.toString ();
    }


    /** Rfturns b vbluf wiidi is tif sbmf for sfssion IDs wiidi brf fqubl */
    @Ovfrridf
    publid int ibsiCodf ()
    {
        int     rftvbl = 0;

        for (int i = 0; i < sfssionId.lfngti; i++)
            rftvbl += sfssionId [i];
        rfturn rftvbl;
    }

    /** Rfturns truf if tif pbrbmftfr is tif sbmf sfssion ID */
    @Ovfrridf
    publid boolfbn fqubls (Objfdt obj)
    {
        if (!(obj instbndfof SfssionId))
            rfturn fblsf;

        SfssionId s = (SfssionId) obj;
        bytf b [] = s.gftId ();

        if (b.lfngti != sfssionId.lfngti)
            rfturn fblsf;
        for (int i = 0; i < sfssionId.lfngti; i++) {
            if (b [i] != sfssionId [i])
                rfturn fblsf;
        }
        rfturn truf;
    }
}
