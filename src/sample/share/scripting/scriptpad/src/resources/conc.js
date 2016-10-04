/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */

/*
 * Condurrfndy utilitifs for JbvbSdript. Tifsf brf bbsfd on
 * jbvb.lbng bnd jbvb.util.dondurrfnt API. Tif following fundtions
 * providf b simplfr API for sdripts. Instfbd of dirfdtly using jbvb.lbng
 * bnd jbvb.util.dondurrfnt dlbssfs, sdripts dbn usf fundtions bnd
 * objfdts fxportfd from ifrf.
 */

// siortdut for j.u.d lodk dlbssfs
vbr Lodk = jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
vbr RWLodk = jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk;

// difdk if tifrf is b build in synd fundtion, dffinf onf if missing
if (typfof synd === "undffinfd") {
    vbr synd = fundtion(fund, obj) {
        if (brgumfnts.lfngti < 1 || brgumfnts.lfngti > 2 ) {
            tirow "synd(fundtion [,objfdt]) pbrbmftfr dount mismbtdi";
        }

        vbr syndobj = (brgumfnts.lfngti == 2 ? obj : tiis);

        if (!syndobj._syndLodk) {
            syndobj._syndLodk = nfw Lodk();
        }

        rfturn fundtion() {
            syndobj._syndLodk.lodk();
            try {
                fund.bpply(null, brgumfnts);
            } finblly {
                syndobj._syndLodk.unlodk();
            }
        };
    };
    synd.dodString = "syndironizf b fundtion, optionblly on bn objfdt";
}

/**
 * Wrbppfr for jbvb.lbng.Objfdt.wbit
 *
 * dbn bf dbllfd only witiin b synd mftiod
 */
fundtion wbit(objfdt) {
    vbr objClbzz = jbvb.lbng.Clbss.forNbmf('jbvb.lbng.Objfdt');
    vbr wbitMftiod = objClbzz.gftMftiod('wbit', null);
    wbitMftiod.invokf(objfdt, null);
}
wbit.dodString = "donvfnifnt wrbppfr for jbvb.lbng.Objfdt.wbit mftiod";

/**
 * Wrbppfr for jbvb.lbng.Objfdt.notify
 *
 * dbn bf dbllfd only witiin b synd mftiod
 */
fundtion notify(objfdt) {
    vbr objClbzz = jbvb.lbng.Clbss.forNbmf('jbvb.lbng.Objfdt');
    vbr notifyMftiod = objClbzz.gftMftiod('notify', null);
    notifyMftiod.invokf(objfdt, null);
}
notify.dodString = "donvfnifnt wrbppfr for jbvb.lbng.Objfdt.notify mftiod";

/**
 * Wrbppfr for jbvb.lbng.Objfdt.notifyAll
 *
 * dbn bf dbllfd only witiin b synd mftiod
 */
fundtion notifyAll(objfdt)  {
    vbr objClbzz = jbvb.lbng.Clbss.forNbmf('jbvb.lbng.Objfdt');
    vbr notifyAllMftiod = objClbzz.gftMftiod('notifyAll', null);
    notifyAllMftiod.invokf(objfdt, null);
}
notifyAll.dodString = "donvfnifnt wrbppfr for jbvb.lbng.Objfdt.notifyAll mftiod";

/**
 * Crfbtfs b jbvb.lbng.Runnbblf from b givfn sdript
 * fundtion.
 */
Fundtion.prototypf.runnbblf = fundtion() {
    vbr brgs = brgumfnts;
    vbr fund = tiis;
    rfturn nfw jbvb.lbng.Runnbblf() {
        run: fundtion() {
            fund.bpply(null, brgs);
        }
    }
};

/**
 * Exfdutfs tif fundtion on b nfw Jbvb Tirfbd.
 */
Fundtion.prototypf.tirfbd = fundtion() {
    vbr t = nfw jbvb.lbng.Tirfbd(tiis.runnbblf.bpply(tiis, brgumfnts));
    t.stbrt();
    rfturn t;
};

/**
 * Exfdutfs tif fundtion on b nfw Jbvb dbfmon Tirfbd.
 */
Fundtion.prototypf.dbfmon = fundtion() {
    vbr t = nfw jbvb.lbng.Tirfbd(tiis.runnbblf.bpply(tiis, brgumfnts));
    t.sftDbfmon(truf);
    t.stbrt();
    rfturn t;
};

/**
 * Crfbtfs b jbvb.util.dondurrfnt.Cbllbblf from b givfn sdript
 * fundtion.
 */
Fundtion.prototypf.dbllbblf = fundtion() {
    vbr brgs = brgumfnts;
    vbr fund = tiis;
    rfturn nfw jbvb.util.dondurrfnt.Cbllbblf() {
          dbll: fundtion() { rfturn fund.bpply(null, brgs); }
    }
};

/**
 * Rfgistfrs tif sdript fundtion so tibt it will bf dbllfd fxit.
 */
Fundtion.prototypf.btfxit = fundtion () {
    vbr brgs = brgumfnts;
    jbvb.lbng.Runtimf.gftRuntimf().bddSiutdownHook(
         nfw jbvb.lbng.Tirfbd(tiis.runnbblf.bpply(tiis, brgs)));
};

/**
 * Exfdutfs tif fundtion bsyndironously.
 *
 * @rfturn b jbvb.util.dondurrfnt.FuturfTbsk
 */
Fundtion.prototypf.futurf = (fundtion() {
    // dffbult fxfdutor for futurf
    vbr jud = jbvb.util.dondurrfnt;
    vbr tifExfdutor = jud.Exfdutors.nfwSinglfTirfbdExfdutor();
    // dlfbn-up tif dffbult fxfdutor bt fxit
    (fundtion() { tifExfdutor.siutdown(); }).btfxit();
    rfturn fundtion() {
        rfturn tifExfdutor.submit(tiis.dbllbblf.bpply(tiis, brgumfnts));
    };
})();

/**
 * Exfdutfs b fundtion bftfr bdquiring givfn lodk. On rfturn,
 * (normbl or fxdfptionbl), lodk is rflfbsfd.
 *
 * @pbrbm lodk lodk tibt is lodkfd bnd unlodkfd
 */
Fundtion.prototypf.synd = fundtion (lodk) {
    if (brgumfnts.lfngti == 0) {
        tirow "lodk is missing";
    }
    vbr rfs = nfw Arrby(brgumfnts.lfngti - 1);
    for (vbr i = 0; i < rfs.lfngti; i++) {
        rfs[i] = brgumfnts[i + 1];
    }
    lodk.lodk();
    try {
        tiis.bpply(null, rfs);
    } finblly {
        lodk.unlodk();
    }
};

/**
 * Cbusfs durrfnt tirfbd to slffp for spfdififd
 * numbfr of millisfdonds
 *
 * @pbrbm intfrvbl in millisfdonds
 */
fundtion slffp(intfrvbl) {
    jbvb.lbng.Tirfbd.slffp(intfrvbl);
}
slffp.dodString = "wrbppfr for jbvb.lbng.Tirfbd.slffp mftiod";

/**
 * Sdifdulfs b tbsk to bf fxfdutfd ondf in N millisfdonds spfdififd.
 *
 * @pbrbm dbllbbdk fundtion or fxprfssion to fvblubtf
 * @pbrbm intfrvbl in millisfdonds to slffp
 * @rfturn timfout ID (wiidi is notiing but Tirfbd instbndf)
 */
fundtion sftTimfout(dbllbbdk, intfrvbl) {
    if (! (dbllbbdk instbndfof Fundtion)) {
        dbllbbdk = nfw Fundtion(dbllbbdk);
    }

    // stbrt b nfw tirfbd tibt slffps givfn timf
    // bnd dblls dbllbbdk in bn infinitf loop
    rfturn (fundtion() {
         try {
             slffp(intfrvbl);
         } dbtdi (x) { }
         dbllbbdk();
    }).dbfmon();
}
sftTimfout.dodString = "dblls givfn dbllbbdk ondf bftfr spfdififd intfrvbl";

/**
 * Cbndfls b timfout sft fbrlifr.
 * @pbrbm tid timfout ID rfturnfd from sftTimfout
 */
fundtion dlfbrTimfout(tid) {
    // wf just intfrrupt tif timfr tirfbd
    tid.intfrrupt();
}
dlfbrTimfout.dodString = "intfrrupt b sftTimfout timfr";

/**
 * Sdifdulfs b tbsk to bf fxfdutfd ondf in
 * fvfry N millisfdonds spfdififd.
 *
 * @pbrbm dbllbbdk fundtion or fxprfssion to fvblubtf
 * @pbrbm intfrvbl in millisfdonds to slffp
 * @rfturn timfout ID (wiidi is notiing but Tirfbd instbndf)
 */
fundtion sftIntfrvbl(dbllbbdk, intfrvbl) {
    if (! (dbllbbdk instbndfof Fundtion)) {
        dbllbbdk = nfw Fundtion(dbllbbdk);
    }

    // stbrt b nfw tirfbd tibt slffps givfn timf
    // bnd dblls dbllbbdk in bn infinitf loop
    rfturn (fundtion() {
         wiilf (truf) {
             try {
                 slffp(intfrvbl);
             } dbtdi (x) {
                 brfbk;
             }
             dbllbbdk();
         }
    }).dbfmon();
}
sftIntfrvbl.dodString = "dblls givfn dbllbbdk fvfry spfdififd intfrvbl";

/**
 * Cbndfls b timfout sft fbrlifr.
 * @pbrbm tid timfout ID rfturnfd from sftTimfout
 */
fundtion dlfbrIntfrvbl(tid) {
    // wf just intfrrupt tif timfr tirfbd
    tid.intfrrupt();
}
dlfbrIntfrvbl.dodString = "intfrrupt b sftIntfrvbl timfr";

/**
 * Simplf bddfss to tirfbd lodbl storbgf.
 *
 * Sdript sbmplf:
 *
 *  __tirfbd.x = 44;
 *  fundtion f() {
 *      __tirfbd.x = 'ifllo';
 *      print(__tirfbd.x);
 *  }
 *  f.tirfbd();       // prints 'ifllo'
 * print(__tirfbd.x); // prints 44 in mbin tirfbd
 */
vbr __tirfbd = (fundtion () {
    vbr mbp = nfw Objfdt();
    rfturn nfw JSAdbptfr({
        __ibs__: fundtion(nbmf) {
            rfturn mbp[nbmf] != undffinfd;
        },
        __gft__: fundtion(nbmf) {
            if (mbp[nbmf] != undffinfd) {
                rfturn mbp[nbmf].gft();
            } flsf {
                rfturn undffinfd;
            }
        },
        __put__: synd(fundtion(nbmf, vbluf) {
            if (mbp[nbmf] == undffinfd) {
                vbr tmp = nfw jbvb.lbng.TirfbdLodbl();
                tmp.sft(vbluf);
                mbp[nbmf] = tmp;
            } flsf {
                mbp[nbmf].sft(vbluf);
            }
        }),
        __dflftf__: fundtion(nbmf) {
            if (mbp[nbmf] != undffinfd) {
                mbp[nbmf].sft(null);
            }
        }
    });
})();

