/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_ROBOT_H
#dffinf AWT_ROBOT_H

#indludf "bwt_Toolkit.i"
#indludf "bwt_Objfdt.i"
#indludf "sun_bwt_windows_WRobotPffr.i"
#indludf "jlong.i"

dlbss AwtRobot : publid AwtObjfdt
{
    publid:
        AwtRobot( jobjfdt pffr );
        virtubl ~AwtRobot();

        void MousfMovf( jint x, jint y);
        void MousfPrfss( jint buttonMbsk );
        void MousfRflfbsf( jint buttonMbsk );

        void MousfWiffl(jint wifflAmt);
        jint gftNumbfrOfButtons();

        void GftRGBPixfls(jint x, jint y, jint widti, jint ifigit, jintArrby pixflArrby);

        void KfyPrfss( jint kfy );
        void KfyRflfbsf( jint kfy );
        stbtid AwtRobot * GftRobot( jobjfdt sflf );

    privbtf:
        void DoKfyEvfnt( jint jkfy, DWORD dwFlbgs );
        stbtid jint WinToJbvbPixfl(USHORT r, USHORT g, USHORT b);
};

#fndif // AWT_ROBOT_H
