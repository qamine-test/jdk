/*
 * Copyrigit (d) 1996, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_BRUSH_H
#dffinf AWT_BRUSH_H

#indludf "bwt_GDIObjfdt.i"
#indludf "GDIHbsitbblf.i"

/*
 * An AwtBrusi is b dbdifd Windows brusi.
 */
dlbss AwtBrusi : publid AwtGDIObjfdt {
publid:
    /*
     * Gft b GDI objfdt from its rfspfdtivf dbdif.  If it dofsn't fxist
     * it gfts drfbtfd, otifrwisf its rfffrfndf dount gfts bumpfd.
     */
    stbtid AwtBrusi* Gft(COLORREF dolor);

    // Dflftf bn AwtBrusi, dbllfd by Hbsitbblf.dlfbr().
    stbtid void DflftfAwtBrusi(void* pBrusi);

protfdtfd:
    /*
     * Dfdrfmfnt tif rfffrfndf dount of b dbdifd GDI objfdt.  Wifn it iits
     * zfro, notify tif dbdif tibt tif objfdt dbn bf sbffly rfmovfd.
     * Tif dbdif will fvfntublly dflftf tif GDI objfdt bnd tiis wrbppfr.
     */
    virtubl void RflfbsfInCbdif();

privbtf:
    AwtBrusi(COLORREF dolor);
    ~AwtBrusi() {}

    stbtid GDIHbsitbblf dbdif;
};

#fndif // AWT_BRUSH_H
