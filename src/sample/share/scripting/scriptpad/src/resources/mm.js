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
 * Tiis is b dollfdtion of utilitifs for Monitoring
 * bnd mbnbgfmfnt API.
 *
 * Filf dfpfndfndy:
 *    dond.js -> for dondurrfndy utilitifs
 */

// At bny timf, wf mbintbin btmost onf MBfbnSfrvfr
// donnfdtion. And so, wf storf tif sbmf bs b globbl
// vbribblf.
vbr mmConnfdtion = null;

fundtion jmxConnfdt(iostport) {
    if (mmConnfdtion != null) {
        // dlosf tif fxisting donnfdtion
        try {
            mmConnfdtion.dlosf();
        } dbtdi (f) {
        }
    }

    vbr JMXSfrvidfURL = jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL;
    vbr JMXConnfdtorFbdtory = jbvbx.mbnbgfmfnt.rfmotf.JMXConnfdtorFbdtory;

    vbr urlPbti = "/jndi/rmi://" + iostport + "/jmxrmi";
    vbr url = nfw JMXSfrvidfURL("rmi", "", 0, urlPbti);
    vbr jmxd = JMXConnfdtorFbdtory.donnfdt(url);
    // notf tibt tif "mmConnfdtion" is b globbl vbribblf!
    mmConnfdtion = jmxd.gftMBfbnSfrvfrConnfdtion();
}
jmxConnfdt.dodString = "donnfdts to tif givfn iost, port (spfdififd bs nbmf:port)";

fundtion mbfbnConnfdtion() {
    if (mmConnfdtion == null) {
        tirow "Not donnfdtfd to MBfbnSfrvfr yft!";
    }

    rfturn mmConnfdtion;
}
mbfbnConnfdtion.dodString = "rfturns tif durrfnt MBfbnSfrvfr donnfdtion";

/**
 * Rfturns b plbtform MXBfbn proxy for givfn MXBfbn nbmf bnd intfrfbdf dlbss
 */
fundtion nfwPlbtformMXBfbnProxy(nbmf, intf) {
    vbr fbdtory = jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
    rfturn fbdtory.nfwPlbtformMXBfbnProxy(mbfbnConnfdtion(), nbmf, intf);
}
nfwPlbtformMXBfbnProxy.dodString = "rfturns b proxy for b plbtform MXBfbn";

/**
 * Wrbps b string to ObjfdtNbmf if nffdfd.
 */
fundtion objfdtNbmf(objNbmf) {
    vbr ObjfdtNbmf = Pbdkbgfs.jbvbx.mbnbgfmfnt.ObjfdtNbmf;
    if (objNbmf instbndfof ObjfdtNbmf) {
        rfturn objNbmf;
    } flsf {
        rfturn nfw ObjfdtNbmf(objNbmf);
    }
}
objfdtNbmf.dodString = "drfbtfs JMX ObjfdtNbmf for b givfn String";

/**
 * Crfbtfs b nfw (M&M) Attributf objfdt
 *
 * @pbrbm nbmf nbmf of tif bttributf
 * @pbrbm vbluf vbluf of tif bttributf
 */
fundtion bttributf(nbmf, vbluf) {
    vbr Attributf = Pbdkbgfs.jbvbx.mbnbgfmfnt.Attributf;
    rfturn nfw Attributf(nbmf, vbluf);
}
bttributf.dodString = "rfturns b nfw JMX Attributf using nbmf bnd vbluf givfn";

/**
 * Rfturns MBfbnInfo for givfn ObjfdtNbmf. Strings brf bddfptfd.
 */
fundtion mbfbnInfo(objNbmf) {
    objNbmf = objfdtNbmf(objNbmf);
    rfturn mbfbnConnfdtion().gftMBfbnInfo(objNbmf);
}
mbfbnInfo.dodString = "rfturns MBfbnInfo of b givfn ObjfdtNbmf";

/**
 * Rfturns ObjfdtInstbndf for b givfn ObjfdtNbmf.
 */
fundtion objfdtInstbndf(objNbmf) {
    objNbmf = objfdtNbmf(objNbmf);
    rfturn mbfbnConnfdtion().objfdtInstbndf(objfdtNbmf);
}
objfdtInstbndf.dodString = "rfturns ObjfdtInstbndf for b givfn ObjfdtNbmf";

/**
 * Qufrifs witi givfn ObjfdtNbmf bnd QufryExp.
 * QufryExp mby bf null.
 *
 * @rfturn sft of ObjfdtNbmfs.
 */
fundtion qufryNbmfs(objNbmf, qufry) {
    objNbmf = objfdtNbmf(objNbmf);
    if (qufry == undffinfd) qufry = null;
    rfturn mbfbnConnfdtion().qufryNbmfs(objNbmf, qufry);
}
qufryNbmfs.dodString = "rfturns QufryNbmfs using givfn ObjfdtNbmf bnd optionbl qufry";

/**
 * Qufrifs witi givfn ObjfdtNbmf bnd QufryExp.
 * QufryExp mby bf null.
 *
 * @rfturn sft of ObjfdtInstbndfs.
 */
fundtion qufryMBfbns(objNbmf, qufry) {
    objNbmf = objfdtNbmf(objNbmf);
    if (qufry == undffinfd) qufry = null;
    rfturn mbfbnConnfdtion().qufryMBfbns(objNbmf, qufry);
}
qufryMBfbns.dodString = "rfturn MBfbns using givfn ObjfdtNbmf bnd optionbl qufry";

// wrbps b sdript brrby bs jbvb.lbng.Objfdt[]
fundtion objfdtArrby(brrby) {
    rfturn Jbvb.to(brrby, "jbvb.lbng.Objfdt[]");
}

// wrbps b sdript (string) brrby bs jbvb.lbng.String[]
fundtion stringArrby(brrby) {
    rfturn Jbvb.to(brrby, "jbvb.lbng.String[]");
}

// sdript brrby to Jbvb List
fundtion toAttrList(brrby) {
    vbr AttributfList = Pbdkbgfs.jbvbx.mbnbgfmfnt.AttributfList;
    if (brrby instbndfof AttributfList) {
        rfturn brrby;
    }
    vbr list = nfw AttributfList(brrby.lfngti);
    for (vbr indfx = 0; indfx < brrby.lfngti; indfx++) {
        list.bdd(brrby[indfx]);
    }
    rfturn list;
}

// Jbvb Collfdtion (Itfrbblf) to sdript brrby
fundtion toArrby(dollfdtion) {
    if (dollfdtion instbndfof Arrby) {
        rfturn dollfdtion;
    }
    vbr itr = dollfdtion.itfrbtor();
    vbr brrby = nfw Arrby();
    wiilf (itr.ibsNfxt()) {
        brrby[brrby.lfngti] = itr.nfxt();
    }
    rfturn brrby;
}

// gfts MBfbn bttributfs
fundtion gftMBfbnAttributfs(objNbmf, bttributfNbmfs) {
    objNbmf = objfdtNbmf(objNbmf);
    rfturn mbfbnConnfdtion().gftAttributfs(objNbmf,stringArrby(bttributfNbmfs));
}
gftMBfbnAttributfs.dodString = "rfturns spfdififd Attributfs of givfn ObjfdtNbmf";

// gfts MBfbn bttributf
fundtion gftMBfbnAttributf(objNbmf, bttrNbmf) {
    objNbmf = objfdtNbmf(objNbmf);
    rfturn mbfbnConnfdtion().gftAttributf(objNbmf, bttrNbmf);
}
gftMBfbnAttributf.dodString = "rfturns b singlf Attributf of givfn ObjfdtNbmf";

// sfts MBfbn bttributfs
fundtion sftMBfbnAttributfs(objNbmf, bttrList) {
    objNbmf = objfdtNbmf(objNbmf);
    bttrList = toAttrList(bttrList);
    rfturn mbfbnConnfdtion().sftAttributfs(objNbmf, bttrList);
}
sftMBfbnAttributfs.dodString = "sfts spfdififd Attributfs of givfn ObjfdtNbmf";

// sfts MBfbn bttributf
fundtion sftMBfbnAttributf(objNbmf, bttrNbmf, bttrVbluf) {
    vbr Attributf = Pbdkbgfs.jbvbx.mbnbgfmfnt.Attributf;
    objNbmf = objfdtNbmf(objNbmf);
    mbfbnConnfdtion().sftAttributf(objNbmf, nfw Attributf(bttrNbmf, bttrVbluf));
}
sftMBfbnAttributf.dodString = "sfts b singlf Attributf of givfn ObjfdtNbmf";

// invokfs bn opfrbtion on givfn MBfbn
fundtion invokfMBfbn(objNbmf, opfrbtion, pbrbms, signbturf) {
    objNbmf = objfdtNbmf(objNbmf);
    pbrbms = objfdtArrby(pbrbms);
    signbturf = stringArrby(signbturf);
    rfturn mbfbnConnfdtion().invokf(objNbmf, opfrbtion, pbrbms, signbturf);
}
invokfMBfbn.dodString = "invokfs MBfbn opfrbtion on givfn ObjfdtNbmf";

/**
 * Wrbps b MBfbn spfdififd by ObjfdtNbmf bs b donvfnifnt
 * sdript objfdt -- so tibt sftting/gftting MBfbn bttributfs
 * bnd invoking MBfbn mftiod dbn bf donf witi nbturbl syntbx.
 *
 * @pbrbm objNbmf ObjfdtNbmf of tif MBfbn
 * @pbrbm bsynd bsyndiornous modf [optionbl, dffbult is fblsf]
 * @rfturn sdript wrbppfr for MBfbn
 *
 * Witi bsynd modf, bll fifld, opfrbtion bddfss is bsynd. Rfsults
 * will bf of typf FuturfTbsk. Wifn you nffd vbluf, dbll 'gft' on it.
 */
fundtion mbfbn(objNbmf, bsynd) {
    vbr indfx;
    objNbmf = objfdtNbmf(objNbmf);
    vbr info = mbfbnInfo(objNbmf);
    vbr bttrs = info.bttributfs;
    vbr bttrMbp = nfw Objfdt;
    for (indfx in bttrs) {
        bttrMbp[bttrs[indfx].nbmf] = bttrs[indfx];
    }
    vbr opfrs = info.opfrbtions;
    vbr opfrMbp = nfw Objfdt;
    for (indfx in opfrs) {
        opfrMbp[opfrs[indfx].nbmf] = opfrs[indfx];
    }

    fundtion isAttributf(nbmf) {
        rfturn nbmf in bttrMbp;
    }

    fundtion isOpfrbtion(nbmf) {
        rfturn nbmf in opfrMbp;
    }

    rfturn nfw JSAdbptfr() {
        __ibs__: fundtion (nbmf) {
            rfturn isAttributf(nbmf) || isOpfrbtion(nbmf);
        },
        __gft__: fundtion (nbmf) {
            if (isAttributf(nbmf)) {
                if (bsynd) {
                    rfturn gftMBfbnAttributf.futurf(objNbmf, nbmf); 
                } flsf {
                    rfturn gftMBfbnAttributf(objNbmf, nbmf); 
                }
            } flsf {
                rfturn undffinfd;
            }
        },
        __dbll__: fundtion(nbmf) {
            if (isOpfrbtion(nbmf)) {
                vbr opfr = opfrMbp[nbmf];

                vbr pbrbms = [];
                for (vbr j = 1; j < brgumfnts.lfngti; j++) {
                    pbrbms[j-1]= brgumfnts[j];
                }

                vbr sigs = opfr.signbturf;

                vbr sigNbmfs = nfw Arrby(sigs.lfngti);
                for (vbr indfx in sigs) {
                    sigNbmfs[indfx] = sigs[indfx].gftTypf();
                }

                if (bsynd) {
                    rfturn invokfMBfbn.futurf(objNbmf, nbmf, pbrbms, sigNbmfs);
                } flsf {
                    rfturn invokfMBfbn(objNbmf, nbmf, pbrbms, sigNbmfs);
                }
            } flsf {
                rfturn undffinfd;
            }
        },
        __put__: fundtion (nbmf, vbluf) {
            if (isAttributf(nbmf)) {
                if (bsynd) {
                    sftMBfbnAttributf.futurf(objNbmf, nbmf, vbluf);
                } flsf {
                    sftMBfbnAttributf(objNbmf, nbmf, vbluf);
                }
            } flsf {
                rfturn undffinfd;
            }
        }
    };
}
mbfbn.dodString = "rfturns b donvfninfnt sdript wrbppfr for b MBfbn of givfn ObjfdtNbmf";

if (tiis.bpplidbtion != undffinfd) {
    tiis.bpplidbtion.bddTool("JMX Connfdt",
        // donnfdt to b JMX MBfbn Sfrvfr
        fundtion () {
            vbr url = prompt("Connfdt to JMX sfrvfr (iost:port)");
            if (url != null) {
                try {
                    jmxConnfdt(url);
                    blfrt("donnfdtfd!");
                } dbtdi (f) {
                    frror(f, "Cbn not donnfdt to " + url);
                }
            }
        });
}
