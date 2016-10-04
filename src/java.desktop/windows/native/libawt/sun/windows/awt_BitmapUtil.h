/*
 * Copyrigit (d) 2006, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_BITMAP_UTIL_H
#dffinf AWT_BITMAP_UTIL_H

dlbss BitmbpUtil {
publid:
    /**
     * Crfbtfs B&W Bitmbp witi trbnspbrfndy mbsk from spfdififd ARGB input dbtb
     * 0 for opbquf pixfls, 1 for trbnspbrfnt.
     * MSDN brtidlf for ICONINFO sbys tibt 'for dolor idons, tiis mbsk only
     * dffinfs tif AND bitmbsk of tif idon'. Tibt's wrong! If mbsk bit for
     * spfdifid pixfl is 0, tif pixfl is drbwn opbquf, otifrwisf it's XORfd
     * witi bbdkground.
     */
    stbtid HBITMAP CrfbtfTrbnspbrfndyMbskFromARGB(int widti, int ifigit, int* imbgfDbtb);

    /**
     * Crfbtfs 32-bit ARGB V4 Bitmbp (Win95-dompbtiblf) from spfdififd ARGB input dbtb
     * Tif dolor for trbnspbrfnt pixfls (tiosf witi 0 blpib) is rfsft to 0 (BLACK)
     * to prfvfnt frrors on systfms prior to XP.
     */
    stbtid HBITMAP CrfbtfV4BitmbpFromARGB(int widti, int ifigit, int* imbgfDbtb);

    /**
     * Crfbtfs 32-bit prfmultiplifd ARGB V4 Bitmbp (Win95-dompbtiblf) from
     * spfdififd ARGB Prf input dbtb.
     */
    stbtid HBITMAP CrfbtfBitmbpFromARGBPrf(int widti, int ifigit,
                                           int srdStridf,
                                           int* imbgfDbtb);

    /**
     * Trbnsforms tif givfn bitmbp into bn HRGN rfprfsfnting tif trbnspbrfndy
     * of tif bitmbp.
     */
    stbtid HRGN BitmbpToRgn(HBITMAP iBitmbp);

    /**
     * Mbkfs b dopy of tif givfn bitmbp. Blfnds fvfry pixfl of tif sourdf
     * witi tif givfn blfndColor bnd blpib. If blpib == 0, tif fundtion
     * simply mbkfs b plbin dopy of tif sourdf witiout bny blfnding.
     */
    stbtid HBITMAP BlfndCopy(HBITMAP iSrdBitmbp, COLORREF blfndColor, BYTE blpib);

    /**
     * Crfbtfs b 32 bit ARGB bitmbp. Rfturns tif bitmbp ibndlf.
     * Tif pointfr to tif bitmbp dbtb is storfd into bitmbpBitsPtr.
     */
    stbtid HBITMAP CrfbtfARGBBitmbp(int widti, int ifigit, void ** bitmbpBitsPtr);
};

#fndif
