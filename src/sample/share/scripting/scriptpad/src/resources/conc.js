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
 * Concurrency utilities for JbvbScript. These bre bbsed on
 * jbvb.lbng bnd jbvb.util.concurrent API. The following functions
 * provide b simpler API for scripts. Instebd of directly using jbvb.lbng
 * bnd jbvb.util.concurrent clbsses, scripts cbn use functions bnd
 * objects exported from here.
 */

// shortcut for j.u.c lock clbsses
vbr Lock = jbvb.util.concurrent.locks.ReentrbntLock;
vbr RWLock = jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;

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
};

/**
 * Executes the function on b new Jbvb Threbd.
 */
Function.prototype.threbd = function() {
    vbr t = new jbvb.lbng.Threbd(this.runnbble.bpply(this, brguments));
    t.stbrt();
    return t;
};

/**
 * Executes the function on b new Jbvb dbemon Threbd.
 */
Function.prototype.dbemon = function() {
    vbr t = new jbvb.lbng.Threbd(this.runnbble.bpply(this, brguments));
    t.setDbemon(true);
    t.stbrt();
    return t;
};

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
};

/**
 * Registers the script function so thbt it will be cblled exit.
 */
Function.prototype.btexit = function () {
    vbr brgs = brguments;
    jbvb.lbng.Runtime.getRuntime().bddShutdownHook(
         new jbvb.lbng.Threbd(this.runnbble.bpply(this, brgs)));
};

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
    };
})();

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
    return new JSAdbpter({
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
    });
})();

