use std::{fmt, io::{self, BufRead, Write}, str};
use std::cmp;

fn main() {
    let mut reader = StdinReader::new();
    let mut writer = io::BufWriter::new(io::stdout().lock());

    let t: i32 = reader.next();
    for _ in 0..t {
        let n: usize = reader.next();
        let mut data: Vec<u64> = Vec::with_capacity(n);
        for _ in 0..n {
            data.push(reader.next());
        }

        let mut h: u64 = cmp::min(data[0], data[1]);
        
        for i in 2..n {
            for j in (i-2)..i {
                h = cmp::max(h, cmp::min(data[i], data[j]));
            }
        }
        
        writeln!(writer, "{}", h).unwrap();
    }
}

struct StdinReader<'a> {
    lines: io::Lines<io::StdinLock<'a>>,
    buffer: Vec<Box<str>>
}

#[allow(unused)]
impl<'a> StdinReader<'a> {
    fn new() -> Self {
        StdinReader {
            lines: io::stdin().lock().lines(),
            buffer: Vec::new()
        }
    }

    fn next<T: str::FromStr>(&mut self) -> T
    where T::Err: fmt::Debug
    {
        while self.buffer.is_empty() {
            self.buffer.extend(self.lines.next().unwrap().unwrap().split_whitespace().map(Box::from).rev());
        }
        self.buffer.pop().unwrap().parse().unwrap()
    }

    fn line<T: str::FromStr, B: FromIterator<T>>(&mut self) -> B
    where T::Err: fmt::Debug
    {
        self.lines.next().unwrap().unwrap().split_whitespace().map(|x| x.parse().unwrap()).collect()
    }
}