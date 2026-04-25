/**
 * Tugas Personal ke-2 (Week 7)
 * Topik: Linked List - Pengelolaan Daftar Buku Perpustakaan
 *
 * Nama: Charys Sonnia Indiriyana
 * NIM: 2902728251
 * Kelas: Computer Science
 */

import java.util.Scanner;

// Class Node untuk merepresentasikan setiap buku
class NodeBuku {
    String kodeBuku;
    String judul;
    String penulis;
    NodeBuku next;

    // Constructor
    NodeBuku(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null;
    }
}

// Class LinkedList untuk mengelola daftar buku
class LinkedListBuku {
    NodeBuku head;
    int jumlahBuku = 0;

    // 1. TAMBAH BUKU (di akhir daftar)
    void tambahBuku(String kodeBuku, String judul, String penulis) {
        // Validasi kodeBuku maksimal 5 karakter
        if (kodeBuku.length() > 5) {
            System.out.println("✗ Kode buku maksimal 5 karakter!");
            return;
        }

        NodeBuku newNode = new NodeBuku(kodeBuku, judul, penulis);

        if (head == null) {
            head = newNode;
        } else {
            NodeBuku current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        jumlahBuku++;
        System.out.println("✓ Data berhasil ditambahkan!");
    }

    // 2. HAPUS BUKU TERAKHIR (Pop)
    void hapusBukuTerakhir() {
        if (head == null) {
            System.out.println("✗ Tidak ada data untuk dihapus.");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            NodeBuku current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        jumlahBuku--;
        System.out.println("✓ Buku terakhir berhasil dihapus!");
    }

    // 3. CARI BUKU berdasarkan kodeBuku
    void cariBuku(String kodeBuku) {
        NodeBuku current = head;
        while (current != null) {
            if (current.kodeBuku.equalsIgnoreCase(kodeBuku)) {
                System.out.println("\n=== BUKU DITEMUKAN ===");
                System.out.println("Kode Buku : " + current.kodeBuku);
                System.out.println("Judul     : " + current.judul);
                System.out.println("Penulis   : " + current.penulis);
                return;
            }
            current = current.next;
        }
        System.out.println("✗ Buku tidak ditemukan.");
    }

    // 4. TAMPILKAN SEMUA BUKU
    void tampilkanSemuaBuku() {
        if (head == null) {
            System.out.println("Belum ada data buku.");
            return;
        }

        System.out.println("\n=== DAFTAR BUKU ===");
        NodeBuku current = head;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". Kode: " + current.kodeBuku +
                    " | Judul: " + current.judul +
                    " | Penulis: " + current.penulis);
            current = current.next;
            no++;
        }
        System.out.println("Total Buku: " + jumlahBuku);
    }
}

// Class Main
public class Perpustakaan {
    public static void main(String[] args) {
        LinkedListBuku list = new LinkedListBuku();
        Scanner sc = new Scanner(System.in);
        int pilih;

        do {
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku (Pop - hapus terakhir)");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = sc.nextInt(); sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan Kode Buku: ");
                    String kode = sc.nextLine();
                    System.out.print("Masukkan Judul: ");
                    String judul = sc.nextLine();
                    System.out.print("Masukkan Penulis: ");
                    String penulis = sc.nextLine();
                    list.tambahBuku(kode, judul, penulis);
                    break;
                case 2:
                    list.hapusBukuTerakhir();
                    break;
                case 3:
                    System.out.print("Masukkan Kode Buku yang dicari: ");
                    String cari = sc.nextLine();
                    list.cariBuku(cari);
                    break;
                case 4:
                    list.tampilkanSemuaBuku();
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }
        } while (pilih != 5);

        sc.close();
    }
}