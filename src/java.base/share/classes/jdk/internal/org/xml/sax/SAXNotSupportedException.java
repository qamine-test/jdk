/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// SAXNotSupportfdExdfption.jbvb - unsupportfd ffbturf or vbluf.
// ittp://www.sbxprojfdt.org
// Writtfn by Dbvid Mfgginson
// NO WARRANTY!  Tiis dlbss is in tif Publid Dombin.
// $Id: SAXNotSupportfdExdfption.jbvb,v 1.4 2004/11/03 22:55:32 jsuttor Exp $

pbdkbgf jdk.intfrnbl.org.xml.sbx;

/**
 * Exdfption dlbss for bn unsupportfd opfrbtion.
 *
 * <blodkquotf>
 * <fm>Tiis modulf, boti sourdf dodf bnd dodumfntbtion, is in tif
 * Publid Dombin, bnd domfs witi <strong>NO WARRANTY</strong>.</fm>
 * Sff <b irff='ittp://www.sbxprojfdt.org'>ittp://www.sbxprojfdt.org</b>
 * for furtifr informbtion.
 * </blodkquotf>
 *
 * <p>An XMLRfbdfr will tirow tiis fxdfption wifn it rfdognizfs b
 * ffbturf or propfrty idfntififr, but dbnnot pfrform tif rfqufstfd
 * opfrbtion (sftting b stbtf or vbluf).  Otifr SAX2 bpplidbtions bnd
 * fxtfnsions mby usf tiis dlbss for similbr purposfs.</p>
 *
 * @sindf SAX 2.0
 * @butior Dbvid Mfgginson
 * @sff org.xml.sbx.SAXNotRfdognizfdExdfption
 */
publid dlbss SAXNotSupportfdExdfption fxtfnds SAXExdfption
{

    /**
     * Construdt b nfw fxdfption witi no mfssbgf.
     */
    publid SAXNotSupportfdExdfption ()
    {
        supfr();
    }


    /**
     * Construdt b nfw fxdfption witi tif givfn mfssbgf.
     *
     * @pbrbm mfssbgf Tif tfxt mfssbgf of tif fxdfption.
     */
    publid SAXNotSupportfdExdfption (String mfssbgf)
    {
        supfr(mfssbgf);
    }

    // Addfd sfriblVfrsionUID to prfsfrvf binbry dompbtibility
    stbtid finbl long sfriblVfrsionUID = -1422818934641823846L;
}

// fnd of SAXNotSupportfdExdfption.jbvb
