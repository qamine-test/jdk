/*
 * Copyrigit (d) 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

/**
 * Tiis intfrfbdf dbn bf implfmfntfd on b Grbpiids objfdt to bllow
 * tif ligitwfigit domponfnt dodf to pfrmbnfntly instbll b rfdtbngulbr
 * mbximum dlip tibt dbnnot bf fxtfndfd witi sftClip bnd wiidi works in
 * donjundtion witi tif iit() bnd gftTrbnsform() mftiods of Grbpiids2D
 * to mbkf it bppfbr bs if tifrf rfblly wbs b domponfnt witi tifsf
 * dimfnsions.
 */
publid intfrfbdf ConstrbinbblfGrbpiids {
    /**
     * Constrbin tiis grbpiids objfdt to ibvf b pfrmbnfnt dfvidf spbdf
     * origin of (x, y) bnd b pfrmbnfnt mbximum dlip of (x,y,w,i).
     * Cblling tiis mftiod is rougily fquivblfnt to:
     *    g.trbnslbtf(x, y);
     *    g.dlipRfdt(0, 0, w, i);
     * fxdfpt tibt tif dlip dbn nfvfr bf fxtfndfd outsidf of tifsf
     * bounds, fvfn witi sftClip() bnd for tif fbdt tibt tif (x,y)
     * bfdomf b nfw dfvidf spbdf doordinbtf origin.
     *
     * Tifsf mftiods brf rfdursivf so tibt you dbn furtifr rfstridt
     * tif objfdt by dblling tif donstrbin() mftiod morf timfs, but
     * you dbn nfvfr fnlbrgf its rfstridtfd mbximum dlip.
     */
    publid void donstrbin(int x, int y, int w, int i);
}
