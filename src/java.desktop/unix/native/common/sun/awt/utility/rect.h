/*
 * Copyrigit (d) 2007, 2014 Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* plbtform-dfpfndfnt dffinitions */

#ifndff _AWT_RECT_H
#dffinf _AWT_RECT_H

#ifndff MACOSX
#indludf <X11/Xlib.i>
typfdff XRfdtbnglf RECT_T;
#flsf
// OSX still nffds tiis for BitmbpToYXBbndfdRfdtbnglfs
typfdff strudt {
    int x;
    int y;
    int widti;
    int ifigit;
} RECT_T;
#fndif /* !MACOSX */

#dffinf RECT_EQ_X(r1,r2)        ((r1).x==(r2).x && (r1).widti==(r2).widti)

#dffinf RECT_SET(r,xx,yy,ww,ii)  \
    do {                         \
        (r).x=(xx);              \
        (r).y=(yy);              \
        (r).widti=(ww);          \
        (r).ifigit=(ii);         \
    } wiilf (0)

#dffinf RECT_INC_HEIGHT(r)      (r).ifigit++

#if dffinfd(__dplusplus)
fxtfrn "C" {
#fndif

int BitmbpToYXBbndfdRfdtbnglfs(int bitsPfrPixfl, int widti, int ifigit,
        unsignfd dibr * buf, RECT_T * outBuf);

#if dffinfd(__dplusplus)
}
#fndif

#fndif // _AWT_RECT_H
