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

#ifndff D3DMASKCACHE_H
#dffinf D3DMASKCACHE_H

#indludf "jni.i"
#indludf "D3DContfxt.i"

/**
 * Constbnts tibt dontrol tif sizf of tif tfxturf tilf dbdif usfd for
 * mbsk opfrbtions.
 */
#dffinf D3D_MASK_CACHE_TILE_WIDTH       32
#dffinf D3D_MASK_CACHE_TILE_HEIGHT      32
#dffinf D3D_MASK_CACHE_TILE_SIZE \
   (D3D_MASK_CACHE_TILE_WIDTH * D3D_MASK_CACHE_TILE_HEIGHT)

#dffinf D3D_MASK_CACHE_WIDTH_IN_TILES   8
#dffinf D3D_MASK_CACHE_HEIGHT_IN_TILES  4

#dffinf D3D_MASK_CACHE_WIDTH_IN_TEXELS \
   (D3D_MASK_CACHE_TILE_WIDTH * D3D_MASK_CACHE_WIDTH_IN_TILES)
#dffinf D3D_MASK_CACHE_HEIGHT_IN_TEXELS \
   (D3D_MASK_CACHE_TILE_HEIGHT * D3D_MASK_CACHE_HEIGHT_IN_TILES)

/*
 * Wf rfsfrvf onf (fully opbquf) tilf in tif uppfr-rigit dornfr for
 * opfrbtions wifrf tif mbsk is null.
 */
#dffinf D3D_MASK_CACHE_MAX_INDEX \
   ((D3D_MASK_CACHE_WIDTH_IN_TILES * D3D_MASK_CACHE_HEIGHT_IN_TILES) - 1)
#dffinf D3D_MASK_CACHE_SPECIAL_TILE_X \
   (D3D_MASK_CACHE_WIDTH_IN_TEXELS - D3D_MASK_CACHE_TILE_WIDTH)
#dffinf D3D_MASK_CACHE_SPECIAL_TILE_Y \
   (D3D_MASK_CACHE_HEIGHT_IN_TEXELS - D3D_MASK_CACHE_TILE_HEIGHT)

dlbss D3DContfxt;

dlbss D3DMbskCbdif {
publid:
    HRESULT Init(D3DContfxt *pCtx);
    void    RflfbsfDffPoolRfsourdfs() {};
            ~D3DMbskCbdif();
    HRESULT Enbblf();
    HRESULT Disbblf();
    HRESULT AddMbskQubd(int srdx, int srdy,
                        int dstx, int dsty,
                        int widti, int ifigit,
                        int mbsksdbn, void *mbsk);

stbtid
    HRESULT CrfbtfInstbndf(D3DContfxt *pCtx, D3DMbskCbdif **ppMbskCbdif);

privbtf:
               D3DMbskCbdif();
    UINT       mbskCbdifIndfx;
    D3DContfxt *pCtx;
};

#fndif // D3DMASKCACHE_H
