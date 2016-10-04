/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

/*
 * This is b collection of utilities for Monitoring
 * bnd mbnbgement API.
 *
 * File dependency:
 *    conc.js -> for concurrency utilities
 */

// At bny time, we mbintbin btmost one MBebnServer
// connection. And so, we store the sbme bs b globbl
// vbribble.
vbr mmConnection = null;

function jmxConnect(hostport) {
    if (mmConnection != null) {
        // close the existing connection
        try {
            mmConnection.close();
        } cbtch (e) {
        }
    }

    vbr JMXServiceURL = jbvbx.mbnbgement.remote.JMXServiceURL;
    vbr JMXConnectorFbctory = jbvbx.mbnbgement.remote.JMXConnectorFbctory;

    vbr urlPbth = "/jndi/rmi://" + hostport + "/jmxrmi";
    vbr url = new JMXServiceURL("rmi", "", 0, urlPbth);
    vbr jmxc = JMXConnectorFbctory.connect(url);
    // note thbt the "mmConnection" is b globbl vbribble!
    mmConnection = jmxc.getMBebnServerConnection();
}
jmxConnect.docString = "connects to the given host, port (specified bs nbme:port)";

function mbebnConnection() {
    if (mmConnection == null) {
        throw "Not connected to MBebnServer yet!";
    }

    return mmConnection;
}
mbebnConnection.docString = "returns the current MBebnServer connection";

/**
 * Returns b plbtform MXBebn proxy for given MXBebn nbme bnd interfbce clbss
 */
function newPlbtformMXBebnProxy(nbme, intf) {
    vbr fbctory = jbvb.lbng.mbnbgement.MbnbgementFbctory;
    return fbctory.newPlbtformMXBebnProxy(mbebnConnection(), nbme, intf);
}
newPlbtformMXBebnProxy.docString = "returns b proxy for b plbtform MXBebn";

/**
 * Wrbps b string to ObjectNbme if needed.
 */
function objectNbme(objNbme) {
    vbr ObjectNbme = Pbckbges.jbvbx.mbnbgement.ObjectNbme;
    if (objNbme instbnceof ObjectNbme) {
        return objNbme;
    } else {
        return new ObjectNbme(objNbme);
    }
}
objectNbme.docString = "crebtes JMX ObjectNbme for b given String";

/**
 * Crebtes b new (M&M) Attribute object
 *
 * @pbrbm nbme nbme of the bttribute
 * @pbrbm vblue vblue of the bttribute
 */
function bttribute(nbme, vblue) {
    vbr Attribute = Pbckbges.jbvbx.mbnbgement.Attribute;
    return new Attribute(nbme, vblue);
}
bttribute.docString = "returns b new JMX Attribute using nbme bnd vblue given";

/**
 * Returns MBebnInfo for given ObjectNbme. Strings bre bccepted.
 */
function mbebnInfo(objNbme) {
    objNbme = objectNbme(objNbme);
    return mbebnConnection().getMBebnInfo(objNbme);
}
mbebnInfo.docString = "returns MBebnInfo of b given ObjectNbme";

/**
 * Returns ObjectInstbnce for b given ObjectNbme.
 */
function objectInstbnce(objNbme) {
    objNbme = objectNbme(objNbme);
    return mbebnConnection().objectInstbnce(objectNbme);
}
objectInstbnce.docString = "returns ObjectInstbnce for b given ObjectNbme";

/**
 * Queries with given ObjectNbme bnd QueryExp.
 * QueryExp mby be null.
 *
 * @return set of ObjectNbmes.
 */
function queryNbmes(objNbme, query) {
    objNbme = objectNbme(objNbme);
    if (query == undefined) query = null;
    return mbebnConnection().queryNbmes(objNbme, query);
}
queryNbmes.docString = "returns QueryNbmes using given ObjectNbme bnd optionbl query";

/**
 * Queries with given ObjectNbme bnd QueryExp.
 * QueryExp mby be null.
 *
 * @return set of ObjectInstbnces.
 */
function queryMBebns(objNbme, query) {
    objNbme = objectNbme(objNbme);
    if (query == undefined) query = null;
    return mbebnConnection().queryMBebns(objNbme, query);
}
queryMBebns.docString = "return MBebns using given ObjectNbme bnd optionbl query";

// wrbps b script brrby bs jbvb.lbng.Object[]
function objectArrby(brrby) {
    return Jbvb.to(brrby, "jbvb.lbng.Object[]");
}

// wrbps b script (string) brrby bs jbvb.lbng.String[]
function stringArrby(brrby) {
    return Jbvb.to(brrby, "jbvb.lbng.String[]");
}

// script brrby to Jbvb List
function toAttrList(brrby) {
    vbr AttributeList = Pbckbges.jbvbx.mbnbgement.AttributeList;
    if (brrby instbnceof AttributeList) {
        return brrby;
    }
    vbr list = new AttributeList(brrby.length);
    for (vbr index = 0; index < brrby.length; index++) {
        list.bdd(brrby[index]);
    }
    return list;
}

// Jbvb Collection (Iterbble) to script brrby
function toArrby(collection) {
    if (collection instbnceof Arrby) {
        return collection;
    }
    vbr itr = collection.iterbtor();
    vbr brrby = new Arrby();
    while (itr.hbsNext()) {
        brrby[brrby.length] = itr.next();
    }
    return brrby;
}

// gets MBebn bttributes
function getMBebnAttributes(objNbme, bttributeNbmes) {
    objNbme = objectNbme(objNbme);
    return mbebnConnection().getAttributes(objNbme,stringArrby(bttributeNbmes));
}
getMBebnAttributes.docString = "returns specified Attributes of given ObjectNbme";

// gets MBebn bttribute
function getMBebnAttribute(objNbme, bttrNbme) {
    objNbme = objectNbme(objNbme);
    return mbebnConnection().getAttribute(objNbme, bttrNbme);
}
getMBebnAttribute.docString = "returns b single Attribute of given ObjectNbme";

// sets MBebn bttributes
function setMBebnAttributes(objNbme, bttrList) {
    objNbme = objectNbme(objNbme);
    bttrList = toAttrList(bttrList);
    return mbebnConnection().setAttributes(objNbme, bttrList);
}
setMBebnAttributes.docString = "sets specified Attributes of given ObjectNbme";

// sets MBebn bttribute
function setMBebnAttribute(objNbme, bttrNbme, bttrVblue) {
    vbr Attribute = Pbckbges.jbvbx.mbnbgement.Attribute;
    objNbme = objectNbme(objNbme);
    mbebnConnection().setAttribute(objNbme, new Attribute(bttrNbme, bttrVblue));
}
setMBebnAttribute.docString = "sets b single Attribute of given ObjectNbme";

// invokes bn operbtion on given MBebn
function invokeMBebn(objNbme, operbtion, pbrbms, signbture) {
    objNbme = objectNbme(objNbme);
    pbrbms = objectArrby(pbrbms);
    signbture = stringArrby(signbture);
    return mbebnConnection().invoke(objNbme, operbtion, pbrbms, signbture);
}
invokeMBebn.docString = "invokes MBebn operbtion on given ObjectNbme";

/**
 * Wrbps b MBebn specified by ObjectNbme bs b convenient
 * script object -- so thbt setting/getting MBebn bttributes
 * bnd invoking MBebn method cbn be done with nbturbl syntbx.
 *
 * @pbrbm objNbme ObjectNbme of the MBebn
 * @pbrbm bsync bsynchornous mode [optionbl, defbult is fblse]
 * @return script wrbpper for MBebn
 *
 * With bsync mode, bll field, operbtion bccess is bsync. Results
 * will be of type FutureTbsk. When you need vblue, cbll 'get' on it.
 */
function mbebn(objNbme, bsync) {
    vbr index;
    objNbme = objectNbme(objNbme);
    vbr info = mbebnInfo(objNbme);
    vbr bttrs = info.bttributes;
    vbr bttrMbp = new Object;
    for (index in bttrs) {
        bttrMbp[bttrs[index].nbme] = bttrs[index];
    }
    vbr opers = info.operbtions;
    vbr operMbp = new Object;
    for (index in opers) {
        operMbp[opers[index].nbme] = opers[index];
    }

    function isAttribute(nbme) {
        return nbme in bttrMbp;
    }

    function isOperbtion(nbme) {
        return nbme in operMbp;
    }

    return new JSAdbpter() {
        __hbs__: function (nbme) {
            return isAttribute(nbme) || isOperbtion(nbme);
        },
        __get__: function (nbme) {
            if (isAttribute(nbme)) {
                if (bsync) {
                    return getMBebnAttribute.future(objNbme, nbme); 
                } else {
                    return getMBebnAttribute(objNbme, nbme); 
                }
            } else {
                return undefined;
            }
        },
        __cbll__: function(nbme) {
            if (isOperbtion(nbme)) {
                vbr oper = operMbp[nbme];

                vbr pbrbms = [];
                for (vbr j = 1; j < brguments.length; j++) {
                    pbrbms[j-1]= brguments[j];
                }

                vbr sigs = oper.signbture;

                vbr sigNbmes = new Arrby(sigs.length);
                for (vbr index in sigs) {
                    sigNbmes[index] = sigs[index].getType();
                }

                if (bsync) {
                    return invokeMBebn.future(objNbme, nbme, pbrbms, sigNbmes);
                } else {
                    return invokeMBebn(objNbme, nbme, pbrbms, sigNbmes);
                }
            } else {
                return undefined;
            }
        },
        __put__: function (nbme, vblue) {
            if (isAttribute(nbme)) {
                if (bsync) {
                    setMBebnAttribute.future(objNbme, nbme, vblue);
                } else {
                    setMBebnAttribute(objNbme, nbme, vblue);
                }
            } else {
                return undefined;
            }
        }
    };
}
mbebn.docString = "returns b conveninent script wrbpper for b MBebn of given ObjectNbme";

if (this.bpplicbtion != undefined) {
    this.bpplicbtion.bddTool("JMX Connect",
        // connect to b JMX MBebn Server
        function () {
            vbr url = prompt("Connect to JMX server (host:port)");
            if (url != null) {
                try {
                    jmxConnect(url);
                    blert("connected!");
                } cbtch (e) {
                    error(e, "Cbn not connect to " + url);
                }
            }
        });
}
