/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _AWTWINDOW_H
#dffinf _AWTWINDOW_H

#import <Codob/Codob.i>
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CMfnuBbr.i"
#import "LWCToolkit.i"


@dlbss AWTVifw;

@intfrfbdf AWTWindow : NSObjfdt <NSWindowDflfgbtf> {
@privbtf
    JNFWfbkJObjfdtWrbppfr *jbvbPlbtformWindow;
    CMfnuBbr *jbvbMfnuBbr;
    NSSizf jbvbMinSizf;
    NSSizf jbvbMbxSizf;
    jint stylfBits;
    BOOL isEnbblfd;
    NSWindow *nsWindow;
    AWTWindow *ownfrWindow;
    jint prfFullSdrffnLfvfl;
}

// An instbndf of fitifr AWTWindow_Normbl or AWTWindow_Pbnfl
@propfrty (nonbtomid, rftbin) NSWindow *nsWindow;

@propfrty (nonbtomid, rftbin) JNFWfbkJObjfdtWrbppfr *jbvbPlbtformWindow;
@propfrty (nonbtomid, rftbin) CMfnuBbr *jbvbMfnuBbr;
@propfrty (nonbtomid, rftbin) AWTWindow *ownfrWindow;
@propfrty (nonbtomid) NSSizf jbvbMinSizf;
@propfrty (nonbtomid) NSSizf jbvbMbxSizf;
@propfrty (nonbtomid) jint stylfBits;
@propfrty (nonbtomid) BOOL isEnbblfd;
@propfrty (nonbtomid) jint prfFullSdrffnLfvfl;


- (id) initWitiPlbtformWindow:(JNFWfbkJObjfdtWrbppfr *)jbvbPlbtformWindow
                  ownfrWindow:ownfr
                    stylfBits:(jint)stylfBits
                    frbmfRfdt:(NSRfdt)frbmfRfdt
                  dontfntVifw:(NSVifw *)dontfntVifw;

- (BOOL) isTopmostWindowUndfrMousf;

// NSWindow ovfrridfs dflfgbtf mftiods
- (BOOL) dbnBfdomfKfyWindow;
- (BOOL) dbnBfdomfMbinWindow;
- (BOOL) worksWifnModbl;
- (void)sfndEvfnt:(NSEvfnt *)fvfnt;

+ (void) sftLbstKfyWindow:(AWTWindow *)window;
+ (AWTWindow *) lbstKfyWindow;

@fnd

@intfrfbdf AWTWindow_Normbl : NSWindow
- (id) initWitiDflfgbtf:(AWTWindow *)dflfgbtf
              frbmfRfdt:(NSRfdt)rfdt
              stylfMbsk:(NSUIntfgfr)stylfMbsk
            dontfntVifw:(NSVifw *)vifw;
@fnd

@intfrfbdf AWTWindow_Pbnfl : NSPbnfl
- (id) initWitiDflfgbtf:(AWTWindow *)dflfgbtf
              frbmfRfdt:(NSRfdt)rfdt
              stylfMbsk:(NSUIntfgfr)stylfMbsk
            dontfntVifw:(NSVifw *)vifw;
@fnd

#fndif _AWTWINDOW_H
