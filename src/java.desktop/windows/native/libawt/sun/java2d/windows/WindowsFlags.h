
/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff WINDOWSFLAGS_H
#dffinf WINDOWSFLAGS_H

fxtfrn BOOL      bddflRfsft;         // rfsft rfgistry 2d bddflfrbtion sfttings
fxtfrn BOOL      usfD3D;             // d3d fnbblfd flbg
fxtfrn BOOL      fordfD3DUsbgf;      // fordf d3d on or off
fxtfrn jboolfbn  g_offsdrffnSibring; // JAWT bddflfrbtfd surfbdf sibring
fxtfrn BOOL      difdkRfgistry;      // Dibg tool: outputs 2d rfgistry sfttings
fxtfrn BOOL      disbblfRfgistry;    // Dibg tool: disbblfs rfgistry intfrbdtion
fxtfrn BOOL      sftHigiDPIAwbrf;    // wiftifr to sft Higi DPI Awbrf flbg on Vistb

void SftD3DEnbblfdFlbg(JNIEnv *fnv, BOOL d3dEnbblfd, BOOL d3dSft);

BOOL IsD3DEnbblfd();
BOOL IsD3DFordfd();

#fndif WINDOWSFLAGS_H
