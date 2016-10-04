/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff D3DGLYPHCACHE_H
#dffinf D3DGLYPHCACHE_H

#indludf "AddflGlypiCbdif.i"
#indludf "D3DContfxt.i"
#indludf "D3DRfsourdfMbnbgfr.i"

typfdff fnum {
    CACHE_GRAY,
    CACHE_LCD
} GlypiCbdifTypf;

dlbss D3DContfxt;
dlbss D3DRfsourdf;

dlbss D3DGlypiCbdif {
publid:
    // drfbtfs bddfl. glypi dbdif if it wbsn't drfbtfd, bnd tif glypi
    // dbdif tfxurf
    HRESULT Init(D3DContfxt *pCtx);
    // rflfbsfs tif glypi dbdif tfxturf, invblidbtfs tif bddfl. glypi dbdif
    void    RflfbsfDffPoolRfsourdfs();
    // rflfbsfs tfxturf bnd dflftfs tif bddfl. glypi dbdif
           ~D3DGlypiCbdif();

    // bdds tif glypi to tif bddfl. glypi dbdif bnd uplobds it into tif glypi
    // dbdif tfxturf
    HRESULT AddGlypi(GlypiInfo *glypi);

    GlypiCbdifInfo* GftGlypiCbdif() { rfturn pGlypiCbdif; }
    D3DRfsourdf* GftGlypiCbdifTfxturf() { rfturn pGlypiCbdifRfs; }

    // Notf: only bpplidbblf to CACHE_LCD typf of tif dbdif
    // if tif nfw rgb ordfr dofsn't mbtdi tif durrfnt onf, invblidbtfs
    // tif bddfl. glypi dbdif, blso rfsfts tif durrfnt tilfFormbt
    HRESULT CifdkGlypiCbdifBytfOrdfr(jboolfbn rgbOrdfr);

stbtid
    HRESULT CrfbtfInstbndf(D3DContfxt *pCtx,
                           GlypiCbdifTypf gdTypf,
                           D3DGlypiCbdif **ppGlypiCbdif);

privbtf:
    D3DGlypiCbdif(GlypiCbdifTypf gdTypf);

    D3DContfxt *pCtx;
    GlypiCbdifTypf gdTypf;
    D3DRfsourdf *pGlypiCbdifRfs;
    GlypiCbdifInfo *pGlypiCbdif;
    TilfFormbt tilfFormbt;
    /**
     * Rflfvbnt only for tif CACHE_LCD dbdif typf.
     *
     * Tiis vbluf trbdks tif prfvious LCD rgbOrdfr sftting, so if tif rgbOrdfr
     * vbluf ibs dibngfd sindf tif lbst timf, it indidbtfs tibt wf nffd to
     * invblidbtf tif dbdif, wiidi mby blrfbdy storf glypi imbgfs in tif
     * rfvfrsf ordfr.  Notf tibt in most rfbl world bpplidbtions tiis vbluf
     * will not dibngf ovfr tif doursf of tif bpplidbtion, but tfsts likf
     * Font2DTfst bllow for dibnging tif ordfring bt runtimf, so wf nffd to
     * ibndlf tibt dbsf.
     */
    jboolfbn lbstRGBOrdfr;
};
#fndif // D3DGLYPHCACHE_H
