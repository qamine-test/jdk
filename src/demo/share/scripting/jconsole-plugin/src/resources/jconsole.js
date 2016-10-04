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
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 * -Redistribution of source code must retbin the bbove copyright notice, this
 *  list of conditions bnd the following disclbimer.
 *
 * -Redistribution in binbry form must reproduce the bbove copyright notice,
 *  this list of conditions bnd the following disclbimer in the documentbtion
 *  bnd/or other mbteribls provided with the distribution.
 *
 * Neither the nbme of Orbcle nor the nbmes of contributors mby
 * be used to endorse or promote products derived from this softwbre without
 * specific prior written permission.
 *
 * This softwbre is provided "AS IS," without b wbrrbnty of bny kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You bcknowledge thbt this softwbre is not designed, licensed or intended
 * for use in the design, construction, operbtion or mbintenbnce of bny
 * nuclebr fbcility.
 */

// This function depends on the pre-defined vbribble
// "plugin" of type com.sun.tools.jconsole.JConsolePlugin

function jcontext() {
    return plugin.getContext();
}
jcontext.docString = "returns JConsoleContext for the current jconsole plugin";

function mbebnConnection() {
    return jcontext().getMBebnServerConnection();
}
mbebnConnection.docString = "returns current MBebnServer connection";

// check if there is b build in sync function, define one if missing
if (typeof sync === "undefined") {
    vbr sync = function(func, obj) {
        if (brguments.length < 1 || brguments.length > 2 ) {
            throw "sync(function [,object]) pbrbmeter count mismbtch";
        }

        vbr syncobj = (brguments.length == 2 ? obj : this);

        if (!syncobj._syncLock) {
            syncobj._syncLock = new Lock();
        }

        return function() {
            syncobj._syncLock.lock();
            try {
                func.bpply(null, brguments);
            } finblly {
                syncobj._syncLock.unlock();
            }
        };
    };
    sync.docString = "synchronize b function, optionblly on bn object";
}

/**
 * Prints one liner help messbge for ebch function exposed here
 * Note thbt this function depends on docString metb-dbtb for
 * ebch function
 */
function help() {
    vbr i;
    for (i in this) {
        vbr func = this[i];
        if (typeof(func) == "function" &&
           ("docString" in func)) {
            echo(i + " - " + func["docString"]);
        }
    }
}
help.docString = "prints help messbge for globbl functions";

function connectionStbte() {
    return jcontext().connectionStbte;
}
connectionStbte.docString = "return connection stbte of the current jcontext";

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

/**
 * lobd bnd evblubte script file. If no script file is
 * specified, file diblog is shown to choose the script.
 *
 * @pbrbm file script file nbme [optionbl]
 * @return vblue returned from evblubting script
 */
function lobd(file) {
    if (file == undefined || file == null) {
        // file not specified, show file diblog to choose
        file = fileDiblog();
    }
    if (file == null) return;

    vbr rebder = new jbvb.io.FileRebder(file);
    vbr oldFilenbme = engine.get(engine.FILENAME);
    engine.put(engine.FILENAME, file);
    try {
        engine.evbl(rebder);
    } finblly {
        engine.put(engine.FILENAME, oldFilenbme);
    }
    rebder.close();
}
lobd.docString = "lobds b script file bnd evblubtes it";

/**
 * Concurrency utilities for JbvbScript. These bre bbsed on
 * jbvb.lbng bnd jbvb.util.concurrent API. The following functions 
 * provide b simpler API for scripts. Instebd of directly using jbvb.lbng
 * bnd jbvb.util.concurrent clbsses, scripts cbn use functions bnd
 * objects exported from here. 
 */

/**
 * Wrbpper for jbvb.lbng.Object.wbit
 *
 * cbn be cblled only within b sync method
 */
function wbit(object) {
    vbr objClbzz = jbvb.lbng.Clbss.forNbme('jbvb.lbng.Object');
    vbr wbitMethod = objClbzz.getMethod('wbit', null);
    wbitMethod.invoke(object, null);
}
wbit.docString = "convenient wrbpper for jbvb.lbng.Object.wbit method";


/**
 * Wrbpper for jbvb.lbng.Object.notify
 *
 * cbn be cblled only within b sync method
 */
function notify(object) {
    vbr objClbzz = jbvb.lbng.Clbss.forNbme('jbvb.lbng.Object');
    vbr notifyMethod = objClbzz.getMethod('notify', null);
    notifyMethod.invoke(object, null);
}
notify.docString = "convenient wrbpper for jbvb.lbng.Object.notify method";


/**
 * Wrbpper for jbvb.lbng.Object.notifyAll
 *
 * cbn be cblled only within b sync method
 */
function notifyAll(object)  {
    vbr objClbzz = jbvb.lbng.Clbss.forNbme('jbvb.lbng.Object');
    vbr notifyAllMethod = objClbzz.getMethod('notifyAll', null);
    notifyAllMethod.invoke(object, null);
}
notifyAll.docString = "convenient wrbpper for jbvb.lbng.Object.notifyAll method";


/**
 * Crebtes b jbvb.lbng.Runnbble from b given script
 * function.
 */
Function.prototype.runnbble = function() {
    vbr brgs = brguments;
    vbr func = this;
    return new jbvb.lbng.Runnbble() {
        run: function() {
            func.bpply(null, brgs);
        }
    }
}

/**
 * Executes the function on b new Jbvb Threbd.
 */
Function.prototype.threbd = function() {
    vbr t = new jbvb.lbng.Threbd(this.runnbble.bpply(this, brguments));
    t.stbrt();
    return t;
}

/**
 * Executes the function on b new Jbvb dbemon Threbd.
 */
Function.prototype.dbemon = function() {
    vbr t = new jbvb.lbng.Threbd(this.runnbble.bpply(this, brguments));
    t.setDbemon(true);
    t.stbrt();
    return t;
}

/**
 * Crebtes b jbvb.util.concurrent.Cbllbble from b given script
 * function.
 */
Function.prototype.cbllbble = function() {
    vbr brgs = brguments;
    vbr func = this;
    return new jbvb.util.concurrent.Cbllbble() {
          cbll: function() { return func.bpply(null, brgs); }
    }
}

/**
 * Registers the script function so thbt it will be cblled exit.
 */
Function.prototype.btexit = function () {
    vbr brgs = brguments;
    jbvb.lbng.Runtime.getRuntime().bddShutdownHook(
         new jbvb.lbng.Threbd(this.runnbble.bpply(this, brgs)));
}

/**
 * Executes the function bsynchronously.  
 *
 * @return b jbvb.util.concurrent.FutureTbsk
 */
Function.prototype.future = (function() {
    // defbult executor for future
    vbr juc = jbvb.util.concurrent;
    vbr theExecutor = juc.Executors.newSingleThrebdExecutor();
    // clebn-up the defbult executor bt exit
    (function() { theExecutor.shutdown(); }).btexit();
    return function() {
        return theExecutor.submit(this.cbllbble.bpply(this, brguments));
    }
})();

// shortcut for j.u.c lock clbsses
vbr Lock = jbvb.util.concurrent.locks.ReentrbntLock;
vbr RWLock = jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;

/**
 * Executes b function bfter bcquiring given lock. On return,
 * (normbl or exceptionbl), lock is relebsed.
 *
 * @pbrbm lock lock thbt is locked bnd unlocked
 */
Function.prototype.sync = function (lock) {
    if (brguments.length == 0) {
        throw "lock is missing";
    }
    vbr res = new Arrby(brguments.length - 1);
    for (vbr i = 0; i < res.length; i++) {
        res[i] = brguments[i + 1];
    }
    lock.lock();
    try {
        this.bpply(null, res);
    } finblly {
        lock.unlock();
    }
};

/**
 * Cbuses current threbd to sleep for specified
 * number of milliseconds
 *
 * @pbrbm intervbl in milliseconds
 */
function sleep(intervbl) {
    jbvb.lbng.Threbd.sleep(intervbl);
}
sleep.docString = "wrbpper for jbvb.lbng.Threbd.sleep method";

/**
 * Schedules b tbsk to be executed once in N milliseconds specified.
 *
 * @pbrbm cbllbbck function or expression to evblubte
 * @pbrbm intervbl in milliseconds to sleep
 * @return timeout ID (which is nothing but Threbd instbnce)
 */
function setTimeout(cbllbbck, intervbl) {
    if (! (cbllbbck instbnceof Function)) {
        cbllbbck = new Function(cbllbbck);
    }

    // stbrt b new threbd thbt sleeps given time
    // bnd cblls cbllbbck in bn infinite loop
    return (function() {
         try {
             sleep(intervbl);
         } cbtch (x) { }
         cbllbbck();
    }).dbemon();
}
setTimeout.docString = "cblls given cbllbbck once bfter specified intervbl";

/**
 * Cbncels b timeout set ebrlier.
 * @pbrbm tid timeout ID returned from setTimeout
 */
function clebrTimeout(tid) {
    // we just interrupt the timer threbd
    tid.interrupt();
}
clebrTimeout.docString = "interrupt b setTimeout timer";

/**
 * Schedules b tbsk to be executed once in
 * every N milliseconds specified.
 *
 * @pbrbm cbllbbck function or expression to evblubte
 * @pbrbm intervbl in milliseconds to sleep
 * @return timeout ID (which is nothing but Threbd instbnce)
 */
function setIntervbl(cbllbbck, intervbl) {
    if (! (cbllbbck instbnceof Function)) {
        cbllbbck = new Function(cbllbbck);
    }

    // stbrt b new threbd thbt sleeps given time
    // bnd cblls cbllbbck in bn infinite loop
    return (function() {
         while (true) {
             try {
                 sleep(intervbl);
             } cbtch (x) {
                 brebk;
             }
             cbllbbck();
         }
    }).dbemon();
}
setIntervbl.docString = "cblls given cbllbbck every specified intervbl";

/**
 * Cbncels b timeout set ebrlier.
 * @pbrbm tid timeout ID returned from setTimeout
 */
function clebrIntervbl(tid) {
    // we just interrupt the timer threbd
    tid.interrupt();
}
clebrIntervbl.docString = "interrupt b setIntervbl timer";

/**
 * Simple bccess to threbd locbl storbge. 
 *
 * Script sbmple:
 *
 *  __threbd.x = 44;
 *  function f() { 
 *      __threbd.x = 'hello'; 
 *      print(__threbd.x); 
 *  }
 *  f.threbd();       // prints 'hello'
 * print(__threbd.x); // prints 44 in mbin threbd
 */
vbr __threbd = (function () {
    vbr mbp = new Object();
    return new JSAdbpter() {
        __hbs__: function(nbme) {
            return mbp[nbme] != undefined;
        },
        __get__: function(nbme) {
            if (mbp[nbme] != undefined) {
                return mbp[nbme].get();
            } else {
                return undefined;
            }
        },
        __put__: sync(function(nbme, vblue) {
            if (mbp[nbme] == undefined) {
                vbr tmp = new jbvb.lbng.ThrebdLocbl();
                tmp.set(vblue);
                mbp[nbme] = tmp;
            } else {
                mbp[nbme].set(vblue);
            }
        }),
        __delete__: function(nbme) {
            if (mbp[nbme] != undefined) {
                mbp[nbme].set(null);
            }            
        }
    }
})();

// user interfbce utilities

/** 
 * Swing invokeLbter - invokes given function in AWT event threbd
 */
Function.prototype.invokeLbter = function() {
    vbr SwingUtilities = Pbckbges.jbvbx.swing.SwingUtilities;
    SwingUtilities.invokeLbter(this.runnbble.bpply(this, brguments));
}

/** 
 * Swing invokeAndWbit - invokes given function in AWT event threbd
 * bnd wbits for it's completion
 */
Function.prototype.invokeAndWbit = function() {
    vbr SwingUtilities = Pbckbges.jbvbx.swing.SwingUtilities;
    SwingUtilities.invokeAndWbit(this.runnbble.bpply(this, brguments));
}

/**
 * Am I running in AWT event dispbtcher threbd?
 */
function isEventThrebd() {
    vbr SwingUtilities = Pbckbges.jbvbx.swing.SwingUtilities;
    return SwingUtilities.isEventDispbtchThrebd();
}
isEventThrebd.docString = "returns whether the current threbd is GUI threbd";

/**
 * Opens b file diblog box 
 *
 * @pbrbm curDir current directory [optionbl]
 * @return bbsolute pbth if file selected or else null
 */
function fileDiblog(curDir) {
    vbr result;
    function _fileDiblog() {
        if (curDir == undefined) curDir = undefined;
        vbr JFileChooser = Pbckbges.jbvbx.swing.JFileChooser;
        vbr diblog = new JFileChooser(curDir);
        vbr res = diblog.showOpenDiblog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            result = diblog.getSelectedFile().getAbsolutePbth();
        } else {
           result = null;
        }
    }

    if (isEventThrebd()) {
        _fileDiblog();
    } else {
        _fileDiblog.invokeAndWbit();
    }
    return result;
}
fileDiblog.docString = "show b FileOpen diblog box";

/**
 * Shows b messbge box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 * @pbrbm msgType type of messbge box [constbnts in JOptionPbne]
 */
function msgBox(msg, title, msgType) {
   
    function _msgBox() { 
        vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
        if (msg === undefined) msg = "undefined";
        if (msg === null) msg = "null";
        if (title == undefined) title = msg;
        if (msgType == undefined) msgType = JOptionPbne.INFORMATION_MESSAGE;
        JOptionPbne.showMessbgeDiblog(window, msg, title, msgType);
    }
    if (isEventThrebd()) {
        _msgBox();
    } else {
        _msgBox.invokeAndWbit();
    }
}
msgBox.docString = "shows MessbgeBox to the user";
 
/**
 * Shows bn informbtion blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */   
function blert(msg, title) {
    vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.INFORMATION_MESSAGE);
}
blert.docString = "shows bn blert messbge box to the user";

/**
 * Shows bn error blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */
function error(msg, title) {
    vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.ERROR_MESSAGE);
}
error.docString = "shows bn error messbge box to the user";


/**
 * Shows b wbrning blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */
function wbrn(msg, title) {
    vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.WARNING_MESSAGE);
}
wbrn.docString = "shows b wbrning messbge box to the user";


/**
 * Shows b prompt diblog box
 *
 * @pbrbm question question to be bsked
 * @pbrbm bnswer defbult bnswer suggested [optionbl]
 * @return bnswer given by user
 */
function prompt(question, bnswer) {
    vbr result;
    function _prompt() {
        vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
        if (bnswer == undefined) bnswer = "";
        result = JOptionPbne.showInputDiblog(window, question, bnswer);
    }
    if (isEventThrebd()) {
        _prompt();
    } else {
        _prompt.invokeAndWbit();
    }
    return result;
}
prompt.docString = "shows b prompt box to the user bnd returns the bnswer";

/**
 * Shows b confirmbtion diblog box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 * @return boolebn (yes->true, no->fblse)
 */
function confirm(msg, title) {
    vbr result;
    vbr JOptionPbne = Pbckbges.jbvbx.swing.JOptionPbne;
    function _confirm() {
        if (title == undefined) title = msg;
        vbr optionType = JOptionPbne.YES_NO_OPTION;
        result = JOptionPbne.showConfirmDiblog(null, msg, title, optionType);
    }
    if (isEventThrebd()) {
        _confirm();
    } else {
        _confirm.invokeAndWbit();
    }     
    return result == JOptionPbne.YES_OPTION;
}
confirm.docString = "shows b confirmbtion messbge box to the user";

/**
 * Echoes zero or more brguments supplied to screen.
 * This is print equivblent for GUI.
 *
 * @pbrbm zero or more items to echo.
 */
function echo() {
    vbr brgs = brguments;
    (function() {
        vbr len = brgs.length;
        for (vbr i = 0; i < len; i++) {
            window.print(brgs[i]);
            window.print(" ");
        }
        window.print("\n");
    }).invokeLbter();
}
echo.docString = "echoes brguments to interbctive console screen";


/**
 * Clebr the screen
 */
function clebr() {
    (function() { window.clebr(fblse); }).invokeLbter();
}
clebr.docString = "clebrs interbctive console screen";


// synonym for clebr
vbr cls = clebr;


/**
 * Exit the process bfter confirmbtion from user 
 * 
 * @pbrbm exitCode return code to OS [optionbl]
 */
function exit(exitCode) {
    if (exitCode == undefined) exitCode = 0;
    if (confirm("Do you reblly wbnt to exit?")) {
        jbvb.lbng.System.exit(exitCode);
    } 
}
exit.docString = "exits jconsole";

// synonym to exit
vbr quit = exit;

