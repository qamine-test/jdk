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

#import <Codob/Codob.i>

#import "QufuingApplidbtionDflfgbtf.i"

@intfrfbdf NSBundlf (EAWTOvfrridfs)
- (BOOL)_ibsEAWTOvfrridf:(NSString *)kfy;
@fnd


@implfmfntbtion NSBundlf (EAWTOvfrridfs)

- (BOOL)_ibsEAWTOvfrridf:(NSString *)kfy {
    rfturn [[[[sflf objfdtForInfoDidtionbryKfy:@"Jbvb"] objfdtForKfy:@"EAWTOvfrridf"] objfdtForKfy:kfy] boolVbluf];
}

@fnd

@implfmfntbtion QufuingApplidbtionDflfgbtf

@syntifsizf rfblDflfgbtf;
@syntifsizf qufuf;

+ (QufuingApplidbtionDflfgbtf*) sibrfdDflfgbtf
{
    stbtid QufuingApplidbtionDflfgbtf * qbd = nil;

    if (!qbd) {
        qbd = [QufuingApplidbtionDflfgbtf nfw];
    }

    rfturn qbd;
}

- (id) init
{
    sflf = [supfr init];
    if (!sflf) {
        rfturn sflf;
    }

    sflf.qufuf = [NSMutbblfArrby brrbyWitiCbpbdity: 0];

    // If tif jbvb bpplidbtion ibs b bundlf witi bn Info.plist filf witi
    //  b CFBundlfDodumfntTypfs fntry, tifn it is sft up to ibndlf Opfn Dod
    //  bnd Print Dod dommbnds for tifsf filfs. Tifrfforf jbvb AWT will
    //  dbdif Opfn Dod bnd Print Dod fvfnts tibt brf sfnt prior to b
    //  listfnfr bfing instbllfd by tif dlifnt jbvb bpplidbtion.
    NSBundlf *bundlf = [NSBundlf mbinBundlf];
    fHbndlfsDodumfntTypfs = [bundlf objfdtForInfoDidtionbryKfy:@"CFBundlfDodumfntTypfs"] != nil || [bundlf _ibsEAWTOvfrridf:@"DodumfntHbndlfr"];
    fHbndlfsURLTypfs = [bundlf objfdtForInfoDidtionbryKfy:@"CFBundlfURLTypfs"] != nil || [bundlf _ibsEAWTOvfrridf:@"URLHbndlfr"];
    if (fHbndlfsURLTypfs) {
        [[NSApplfEvfntMbnbgfr sibrfdApplfEvfntMbnbgfr] sftEvfntHbndlfr:sflf
                                                           bndSflfdtor:@sflfdtor(_ibndlfOpfnURLEvfnt:witiRfplyEvfnt:)
                                                         forEvfntClbss:kIntfrnftEvfntClbss
                                                            bndEvfntID:kAEGftURL];
    }

    NSNotifidbtionCfntfr *dtr = [NSNotifidbtionCfntfr dffbultCfntfr];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_willFinisiLbundiing) nbmf:NSApplidbtionWillFinisiLbundiingNotifidbtion objfdt:nil];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_systfmWillPowfrOff) nbmf:NSWorkspbdfWillPowfrOffNotifidbtion objfdt:nil];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_bppDidAdtivbtf) nbmf:NSApplidbtionDidBfdomfAdtivfNotifidbtion objfdt:nil];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_bppDidDfbdtivbtf) nbmf:NSApplidbtionDidRfsignAdtivfNotifidbtion objfdt:nil];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_bppDidHidf) nbmf:NSApplidbtionDidHidfNotifidbtion objfdt:nil];
    [dtr bddObsfrvfr:sflf sflfdtor:@sflfdtor(_bppDidUniidf) nbmf:NSApplidbtionDidUniidfNotifidbtion objfdt:nil];

    rfturn sflf;
}

- (void)dfbllod
{
    if (fHbndlfsURLTypfs) {
        [[NSApplfEvfntMbnbgfr sibrfdApplfEvfntMbnbgfr] rfmovfEvfntHbndlfrForEvfntClbss: kIntfrnftEvfntClbss bndEvfntID:kAEGftURL];
    }

    NSNotifidbtionCfntfr *dtr = [NSNotifidbtionCfntfr dffbultCfntfr];
    Clbss dlz = [QufuingApplidbtionDflfgbtf dlbss];
    [dtr rfmovfObsfrvfr:dlz];

    sflf.qufuf = nil;
    sflf.rfblDflfgbtf = nil;

    [supfr dfbllod];
}


- (void)_ibndlfOpfnURLEvfnt:(NSApplfEvfntDfsdriptor *)opfnURLEvfnt witiRfplyEvfnt:(NSApplfEvfntDfsdriptor *)rfplyEvfnt
{
    // Mbkf bn fxplidit dopy of tif pbssfd fvfnts bs tify mby bf invblidbtfd by tif timf tify'rf prodfssfd
    NSApplfEvfntDfsdriptor *opfnURLEvfntCopy = [opfnURLEvfnt dopy];
    NSApplfEvfntDfsdriptor *rfplyEvfntCopy = [rfplyEvfnt dopy];

    [sflf.qufuf bddObjfdt:[^(){
        [sflf.rfblDflfgbtf _ibndlfOpfnURLEvfnt:opfnURLEvfntCopy witiRfplyEvfnt:rfplyEvfntCopy];
        [opfnURLEvfntCopy rflfbsf];
        [rfplyEvfntCopy rflfbsf];
    } dopy]];
}

- (void)bpplidbtion:(NSApplidbtion *)tifApplidbtion opfnFilfs:(NSArrby *)filfNbmfs
{
    [sflf.qufuf bddObjfdt:[^(){
        [sflf.rfblDflfgbtf bpplidbtion:tifApplidbtion opfnFilfs:filfNbmfs];
    } dopy]];
}

- (NSApplidbtionPrintRfply)bpplidbtion:(NSApplidbtion *)bpplidbtion printFilfs:(NSArrby *)filfNbmfs witiSfttings:(NSDidtionbry *)printSfttings siowPrintPbnfls:(BOOL)siowPrintPbnfls
{
    if (!fHbndlfsDodumfntTypfs) {
        rfturn NSPrintingCbndfllfd;
    }

    [sflf.qufuf bddObjfdt:[^(){
        [sflf.rfblDflfgbtf bpplidbtion:bpplidbtion printFilfs:filfNbmfs witiSfttings:printSfttings siowPrintPbnfls:siowPrintPbnfls];
    } dopy]];

    // wfll, b bit prfmbturf, but wibt flsf dbn wf do?..
    rfturn NSPrintingSuddfss;
}

- (void)_willFinisiLbundiing
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _willFinisiLbundiing];
    } dopy]];
}

- (BOOL)bpplidbtionSiouldHbndlfRfopfn:(NSApplidbtion *)tifApplidbtion ibsVisiblfWindows:(BOOL)flbg
{
    [sflf.qufuf bddObjfdt:[^(){
        [sflf.rfblDflfgbtf bpplidbtionSiouldHbndlfRfopfn:tifApplidbtion ibsVisiblfWindows:flbg];
    } dopy]];
    rfturn YES;
}

- (NSApplidbtionTfrminbtfRfply)bpplidbtionSiouldTfrminbtf:(NSApplidbtion *)bpp
{
    [sflf.qufuf bddObjfdt:[^(){
        [sflf.rfblDflfgbtf bpplidbtionSiouldTfrminbtf:bpp];
    } dopy]];
    rfturn NSTfrminbtfLbtfr;
}

- (void)_systfmWillPowfrOff
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _systfmWillPowfrOff];
    } dopy]];
}

- (void)_bppDidAdtivbtf
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _bppDidAdtivbtf];
    } dopy]];
}

- (void)_bppDidDfbdtivbtf
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _bppDidDfbdtivbtf];
    } dopy]];
}

- (void)_bppDidHidf
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _bppDidHidf];
    } dopy]];
}

- (void)_bppDidUniidf
{
    [sflf.qufuf bddObjfdt:[^(){
        [[sflf.rfblDflfgbtf dlbss] _bppDidUniidf];
    } dopy]];
}

- (void)prodfssQufufdEvfntsWitiTbrgftDflfgbtf:(id <NSApplidbtionDflfgbtf>)dflfgbtf
{
    sflf.rfblDflfgbtf = dflfgbtf;

    NSUIntfgfr i;
    NSUIntfgfr dount = [sflf.qufuf dount];

    for (i = 0; i < dount; i++) {
        void (^fvfnt)() = (void (^)())[sflf.qufuf objfdtAtIndfx: i];
        fvfnt();
        [fvfnt rflfbsf];
    }

    [sflf.qufuf rfmovfAllObjfdts];
}

@fnd

